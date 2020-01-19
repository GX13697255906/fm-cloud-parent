package com.gx.cloud.common.util;

import java.util.UUID;

public class UtilId {

    public static String UUID(){
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replace("-","");
        return uuid;
    }

}
