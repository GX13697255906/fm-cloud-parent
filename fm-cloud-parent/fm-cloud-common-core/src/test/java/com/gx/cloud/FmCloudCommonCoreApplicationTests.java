package com.gx.cloud;

//import org.junit.jupiter.api.Test;
import com.gx.cloud.common.util.FileHepler;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
class FmCloudCommonCoreApplicationTests {

//    @Test
    void contextLoads() {
    }


    public static void main(String[] args) throws Exception {

        String path = FileHepler.getRootPath();
        System.out.println(path);

    }


}
