package com.prc.springbootfile.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class FileUtils {

    public static Map<String,Object> formatPath(String coursePath){
        log.info("当前项目的相对路径:" + coursePath);
        String downloadPath = coursePath.replaceAll("\\\\","/");
        log.info("格式化后的路径:" + downloadPath);
        String path = downloadPath.substring(0,downloadPath.length() -1);
        log.info("文件夹路径:" + path);
        Map<String,Object> data = new HashMap<String, Object>();
        data.put("downloadPath",downloadPath);
        data.put("path",path);
        return data;
    }
}
