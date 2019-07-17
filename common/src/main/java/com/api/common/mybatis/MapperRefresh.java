package com.api.common.mybatis;


import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.NestedIOException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.io.*;
import java.lang.reflect.Field;
import java.util.*;

@ConfigurationProperties("mybatis.hot.reload")
@Component
public class MapperRefresh implements java.lang.Runnable{
    /**
     * 刷新MyBatis Mapper XML 线程
     * @author ThinkGem
     * @version 2016-5-29
     */

        @Autowired
        private SqlSession sqlSession;
        //项目中mapper.xml的路径
        private String mapperLocation;

        public static Logger log = LoggerFactory.getLogger(MapperRefresh.class);


        private  boolean enabled;         // 是否启用Mapper刷新线程功能
        private static   boolean refresh;         // 刷新启用后，是否启动了刷新线程

        private Set<String> location;         // Mapper实际资源路径

        private Resource[] mapperLocations ;     // Mapper资源路径// MyBatis配置对象

        private Long beforeTime = 0L;           // 上一次刷新时间
        private  int delaySeconds = 10;        // 延迟刷新秒数
        private  int sleepSeconds;        // 休眠时间
        private  String mappingPath;      // xml文件夹匹配字符串，需要根据需要修改


        @Override
        public void run() {

            beforeTime = System.currentTimeMillis();

            log.debug("[location] " + location);

            if (enabled) {
                // 启动刷新线程
                final MapperRefresh runnable = this;
                new Thread(() -> {

                    if (location == null){
                        location = new HashSet<>();
                        log.debug("MapperLocation's length:" + mapperLocation);
                        try {
                            mapperLocations = new PathMatchingResourcePatternResolver().getResources(mapperLocation);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        for (Resource mapperLocation : mapperLocations) {
                            String s = mapperLocation.toString().replaceAll("\\\\", "/");
                            s = s.substring("file [".length(), s.lastIndexOf(mappingPath) + mappingPath.length());
                            if (!location.contains(s)) {
                                location.add(s);
                                log.debug("Location:" + s);
                            }
                        }
                        log.debug("Locarion's size:" + location.size());
                    }

                    try {
                        Thread.sleep(delaySeconds * 1000);
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                    refresh = true;

                    log.info("========= Enabled refresh mybatis mapper =========");

                    while (true) {
                        try {
                            for (String s : location) {
                                runnable.refresh(s, beforeTime);
                            }
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                        try {
                            Thread.sleep(sleepSeconds * 1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }, "MyBatis-Mapper-Refresh").start();
            }
        }

        /**
         * 执行刷新
         * @param filePath 刷新目录
         * @param beforeTime 上次刷新时间
         * @throws NestedIOException 解析异常
         * @throws FileNotFoundException 文件未找到
         * @author ThinkGem
         */
        @SuppressWarnings({ "rawtypes", "unchecked" })
        private void refresh(String filePath, Long beforeTime) throws Exception {

            // 本次刷新时间
            Long refrehTime = System.currentTimeMillis();

            // 获取需要刷新的Mapper文件列表
            List<File> fileList = this.getRefreshFile(new File(filePath), beforeTime);

            for (File file : fileList) {
                InputStream inputStream = new FileInputStream(file);
                String resource = file.getAbsolutePath();
                try {

                    // 清理原有资源，更新为自己的StrictMap方便，增量重新加载
                    String[] mapFieldNames = new String[]{
                            "mappedStatements", "caches",
                            "resultMaps", "parameterMaps",
                            "keyGenerators", "sqlFragments"
                    };
                    for (String fieldName : mapFieldNames) {

                        Field field = sqlSession.getConfiguration().getClass().getDeclaredField(fieldName);
                        field.setAccessible(true);
                        Map map = ((Map) field.get(sqlSession.getConfiguration()));
                        if (!(map instanceof StrictMap)) {
                            Map newMap = new StrictMap(StringUtils.capitalize(fieldName) + "collection");
                            for (Object key : map.keySet()) {
                                try {
                                    newMap.put(key, map.get(key));
                                } catch (IllegalArgumentException ex) {
                                    newMap.put(key, ex.getMessage());
                                }
                            }
                            field.set(sqlSession.getConfiguration(), newMap);
                        }
                    }

                    // 清理已加载的资源标识，方便让它重新加载。
                    Field loadedResourcesField = sqlSession.getConfiguration().getClass().getDeclaredField("loadedResources");
                    loadedResourcesField.setAccessible(true);
                    Set loadedResourcesSet = ((Set) loadedResourcesField.get(sqlSession.getConfiguration()));
                    loadedResourcesSet.remove(resource);

                    //重新编译加载资源文件。
                    XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(inputStream, sqlSession.getConfiguration(),
                            resource, sqlSession.getConfiguration().getSqlFragments());
                    xmlMapperBuilder.parse();
                } catch (Exception e) {
                    throw new NestedIOException("Failed to parse mapping resource: '" + resource + "'", e);
                } finally {
                    ErrorContext.instance().reset();
                }
                log.debug("Refresh filename: " + file.getName());
            }
            // 如果刷新了文件，则修改刷新时间，否则不修改
            if (fileList.size() > 0) {
                this.beforeTime = refrehTime;
            }
        }

        /**
         * 获取需要刷新的文件列表
         * @param dir 目录
         * @param beforeTime 上次刷新时间
         * @return 刷新文件列表
         */
        private List<File> getRefreshFile(File dir, Long beforeTime) {
            List<File> fileList = new ArrayList<File>();

            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        fileList.addAll(this.getRefreshFile(file, beforeTime));
                    } else if (file.isFile()) {
                        if (this.checkFile(file, beforeTime)) {
                            fileList.add(file);
                        }
                    } else {
                        log.error("Error file." + file.getName());
                    }
                }
            }
            return fileList;
        }

        /**
         * 判断文件是否需要刷新
         * @param file 文件
         * @param beforeTime 上次刷新时间
         * @return 需要刷新返回true，否则返回false
         */
        private boolean checkFile(File file, Long beforeTime) {
            return file.lastModified() > beforeTime;
        }

        /**
         * 重写 org.apache.ibatis.session.Configuration.StrictMap 类
         * 来自 MyBatis3.4.0版本，修改 put 方法，允许反复 put更新。
         */
        public static class StrictMap<V> extends HashMap<String, V> {

            private static final long serialVersionUID = -4950446264854982944L;
            private String name;

            public StrictMap(String name, int initialCapacity, float loadFactor) {
                super(initialCapacity, loadFactor);
                this.name = name;
            }

            public StrictMap(String name, int initialCapacity) {
                super(initialCapacity);
                this.name = name;
            }

            StrictMap(String name) {
                super();
                this.name = name;
            }

            public StrictMap(String name, Map<String, ? extends V> m) {
                super(m);
                this.name = name;
            }

            @SuppressWarnings("unchecked")
            public V put(String key, V value) {
                // ThinkGem 如果现在状态为刷新，则刷新(先删除后添加)
                if (refresh) {
                    remove(key);
//                MapperRefresh.log.debug("refresh key:" + key.substring(key.lastIndexOf(".") + 1));
                }
                // ThinkGem end
                if (containsKey(key)) {
                    throw new IllegalArgumentException(name + " already contains value for " + key);
                }
                if (key.contains(".")) {
                    final String shortKey = getShortName(key);
                    if (super.get(shortKey) == null) {
                        super.put(shortKey, value);
                    } else {
                        super.put(shortKey, (V) new Ambiguity(shortKey));
                    }
                }
                return super.put(key, value);
            }

            public V get(Object key) {
                V value = super.get(key);
                if (value == null) {
                    throw new IllegalArgumentException(name + " does not contain value for " + key);
                }
                if (value instanceof Ambiguity) {
                    throw new IllegalArgumentException(((Ambiguity) value).getSubject() + " is ambiguous in " + name
                            + " (try using the full name including the namespace, or rename one of the entries)");
                }
                return value;
            }

            private String getShortName(String key) {
                final String[] keyparts = key.split("\\.");
                return keyparts[keyparts.length - 1];
            }

            static class Ambiguity {
                private String subject;

                Ambiguity(String subject) {
                    this.subject = subject;
                }

                String getSubject() {
                    return subject;
                }
            }
        }

    public  boolean isEnabled() {
        return enabled;
    }

    public  void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public  int getDelaySeconds() {
        return delaySeconds;
    }

    public  void setDelaySeconds(int delaySeconds) {
        this.delaySeconds = delaySeconds;
    }

    public  int getSleepSeconds() {
        return sleepSeconds;
    }

    public  void setSleepSeconds(int sleepSeconds) {
        this.sleepSeconds = sleepSeconds;
    }

    public  String getMappingPath() {
        return mappingPath;
    }

    public  void setMappingPath(String mappingPath) {
        this.mappingPath = mappingPath;
    }

    public String getMapperLocation() {
        return mapperLocation;
    }

    public void setMapperLocation(String mapperLocation) {
        this.mapperLocation = mapperLocation;
    }

    public boolean isRefresh() {
        return refresh;
    }

    public void setRefresh(boolean refresh) {
        MapperRefresh.refresh = refresh;
    }
}

