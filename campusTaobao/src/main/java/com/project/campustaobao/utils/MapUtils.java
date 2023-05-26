package com.project.campustaobao.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MapUtils {
    public static List<Object> getParameterList(Map<Object,Object> map,Object name){
        //若不存在该属性，则返回null
        if(map.get(name) == null){
            return null;
        }
        List<Object> list = new ArrayList<>(map.size());
        Set<Object> keySet = map.keySet();
        for(Object key : keySet){

        }
        return list;
    }
}
