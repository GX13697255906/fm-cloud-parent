package com.gx.cloud.common.util;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.cglib.beans.BeanMap;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class UtilMap {

    public static <T> T mapToObject(Map<String,Object> map,Class<T> beanClass) {
        if(MapUtils.isEmpty(map)){
            return null;
        }else {
            T obj = null;
            try {
                obj = beanClass.newInstance();
                BeanUtils.populate(obj,map);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            return obj;
        }
    }

    public static <T> List<T> mapToObject(List<Map<String,Object>> list, Class<T> beanClass){
        if(list.size() == 0 || list.isEmpty()){
            return null;
        }else {
            List<T> result = new ArrayList<>();
            try {
                for(Map<String,Object> map:list){
                    T obj = beanClass.newInstance();
                    BeanUtils.populate(obj,map);
                    result.add(obj);
                }
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            return result;
        }
    }

    public static <T> Map<String, Object> objectToMap(T bean) {
        Map<String, Object> map = new TreeMap<>();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                map.put(key + "", beanMap.get(key));
            }
        }
        return map;
    }

    public static List<Double> mergeMap(Map<String,Object> map1, Map<String,Object> map2, String title){

        boolean flag1 = false;
        boolean flag2 = false;
        List<Double> result = new ArrayList<>();
        List<String> list1 = UtilString.convertTimeFormatValue(map1,title);
        List<String> list2 = UtilString.convertTimeFormatValue(map2,title);
        if(list1 != null){
            if(list1.size() == 96){
                flag1 = true;
            }
        }
        if(list2 != null){
            if(list2.size() == 96){
                flag2 = true;
            }
        }
        if(flag1 == true && flag2 == true){
            for(int i = 0;i<96;i++){
                double merge = UtilString.stringToDouble(list1.get(i))+UtilString.stringToDouble(list2.get(i));
                result.add(merge);
            }
        }else if(flag1 == true && flag2 == false){
            for(int i = 0;i<96;i++){
                double merge = UtilString.stringToDouble(list1.get(i));
                result.add(merge);
            }
        }else if(flag1 == false && flag2 == true){
            for(int i = 0;i<96;i++){
                double merge = UtilString.stringToDouble(list2.get(i));
                result.add(merge);
            }
        }else {
            result = new ArrayList<>();
        }

        return result;

    }

}
