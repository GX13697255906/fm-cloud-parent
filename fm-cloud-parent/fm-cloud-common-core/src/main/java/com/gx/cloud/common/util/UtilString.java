package com.gx.cloud.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

@Slf4j
public class UtilString {

    public static List<String> keys = null;

    public static double stringToDouble(String str){
        double result = 0;
        boolean flag = true;
        if(!StringUtils.isBlank(str)){
            try {
                result = Double.parseDouble(str);
            } catch (NumberFormatException e) {
                flag = false;
            }
        }
        if(flag == true){
            return result;
        }else {
            return 0;
        }

    }

    public static Map<String,Object> convertTimeFormat(Map<String,Object> targetMap,String targerStr){
        List<String> vals = new ArrayList<>();
        int targetStrLen = targerStr.length();
        Map<String,Object> result = new HashMap<>();
        Set<String> keySet = targetMap.keySet();
        Iterator<String> iterator = keySet.iterator();
        while (iterator.hasNext()){
            String key = iterator.next();
            if(key.contains(targerStr) && key.length()>=targetStrLen){
                if(key.substring(0,targetStrLen).equals(targerStr)){
                    String first = key.substring(targetStrLen,targetStrLen+2);
                    String end = key.substring(key.length()-2);
                    String tarKey = first+":"+end;
                    if(keys == null){
                        keys = new ArrayList<>();
                    }else if(keys.size()>=96){
                    }else {
                        keys.add(tarKey);
                    }
                    vals.add(MapUtils.getString(targetMap,key));
                }
            }

        }
         result.put("keys",keys);
         result.put("values",vals);
        return result;
    }

    public static List<String> convertTimeFormatValue(Map<String,Object> targetMap,String targerStr){
        List<String> vals = new ArrayList<>();
        int targetStrLen = targerStr.length();
        Set<String> keySet = targetMap.keySet();
        Iterator<String> iterator = keySet.iterator();
        while (iterator.hasNext()){
            String key = iterator.next();
            if(key.contains(targerStr) && key.length()>=targetStrLen){
                if(key.substring(0,targetStrLen).equals(targerStr)){
                    String first = key.substring(targetStrLen,targetStrLen+2);
                    String end = key.substring(key.length()-2);
                    String tarKey = first+":"+end;
                    if(keys == null){
                        keys = new ArrayList<>();
                    }else if(keys.size()>=96){

                    }else {
                        keys.add(tarKey);
                    }
                    vals.add(MapUtils.getString(targetMap,key));
                }
            }

        }
        return vals;
    }

}
