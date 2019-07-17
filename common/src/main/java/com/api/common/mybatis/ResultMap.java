package com.api.common.mybatis;

import java.util.HashMap;

public class ResultMap<K,V> extends HashMap<K,V> {
    @Override
    public V put(K key, V value) {
        if(key instanceof  String) key = (K) underlineToHump(key.toString());
        return super.put(key, value);
    }

    private static String underlineToHump(String para){
        StringBuilder result=new StringBuilder();
        String[] a = para.split("_");
        for(String s:a){
            if (!para.contains("_")) {
                result.append(s);
                continue;
            }
            if(result.length()==0){
                result.append(s.toLowerCase());
            }else{
                result.append(s.substring(0, 1).toUpperCase());
                result.append(s.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }
}
