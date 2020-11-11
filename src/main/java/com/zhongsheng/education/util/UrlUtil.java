package com.zhongsheng.education.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class UrlUtil {

    public static String getUrl(){
        //return System.getProperty("user.dir");
        File path = null;
        try {
            path = new File(ResourceUtils.getURL("classpath:").getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if(!path.exists()) path = new File("");
        /*String url = null;
        String str = null;
        if(path.getAbsolutePath()!=null){
            url = path.getAbsolutePath().substring(0,path.getAbsolutePath().lastIndexOf("target"));
            str =  url.substring(0,url.lastIndexOf("\\"));
        }*/

        return  path.getAbsolutePath();
    }

}
