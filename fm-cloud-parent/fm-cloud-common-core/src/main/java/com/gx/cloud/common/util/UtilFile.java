package com.gx.cloud.common.util;

import java.io.File;

public class UtilFile {

    /**
     * 创建
     * @param filePath
     * @return
     */
    public static String createFile(String filePath){
        File file = new File(filePath);
        /*验证文件是否存在*/
        if(!file.exists()){
            /*创建文件及其父文件夹*/
            file.mkdirs();
        }
        return file.getPath();
    }

}
