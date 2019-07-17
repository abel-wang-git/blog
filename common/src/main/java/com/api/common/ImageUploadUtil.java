package com.api.common;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2017/4/6.
 */
public class ImageUploadUtil {

    // 图片类型
    private static List<String> fileTypes = new ArrayList<> ();

    static {
        fileTypes.add (".jpg");
        fileTypes.add (".jpeg");
        fileTypes.add (".bmp");
        fileTypes.add (".gif");
        fileTypes.add (".png");
    }

    /**
     * 图片上传
     *
     * @param request request
     * @param DirectoryName 文件上传目录：比如upload(无需带前面的/) upload/news ..
     * @return  图片路径
     * @throws IllegalStateException
     * @throws IOException
     * @Title upload
     */
    public static String upload (HttpServletRequest request, String DirectoryName) throws IllegalStateException, IOException {
        // 创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession ().getServletContext ());
        // 图片名称
        StringBuilder fileName = new StringBuilder();
        // 判断 request 是否有文件上传,即多部分请求
        if (multipartResolver.isMultipart (request)) {
            // 转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            // 取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames ();
            while (iter.hasNext ()) {
                // 记录上传过程起始时的时间，用来计算上传时间
                // int pre = (int) System.currentTimeMillis();
                // 取得上传文件
                MultipartFile file = multiRequest.getFile (iter.next ());
                if (file != null) {
                    // 取得当前上传文件的文件名称
                    String myFileName = file.getOriginalFilename ();
                    // 如果名称不为“”,说明该文件存在，否则说明该文件不存在
                    assert myFileName != null;
                    if (!myFileName.trim().equals("")) {
                        // 获得图片的原始名称
                        String originalFilename = file.getOriginalFilename ();
                        // 获得图片后缀名称,如果后缀不为图片格式，则不上传
                        String suffix = originalFilename.substring (originalFilename.lastIndexOf (".")).toLowerCase ();
                        originalFilename=originalFilename.replace(suffix,"");
                        if (!fileTypes.contains (suffix)) {
                            continue;
                        }

                        File upload = new File(DirectoryName + File.separator);

                        if (!upload.exists ()) {
                            upload.mkdirs ();
                        }
                        // 重命名上传后的文件名
                        fileName.append(originalFilename).append("_").append(new Date().getTime()).append(suffix).append(",");
                        // 定义上传路径 .../upload/111112323.jpg
                        File uploadFile = new File (upload + File.separator +originalFilename+"_"+new Date ().getTime () + suffix);
                        file.transferTo (uploadFile);
                    }
                }
            }
        }
        return fileName.toString();
    }
}
