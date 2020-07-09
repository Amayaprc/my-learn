package com.prc.springbootfile.config;

import com.prc.springbootfile.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * WebMvc 扩展配置类，应用一启动，本类就会执行
 */
@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {

    //请求 url 中的资源映射，不推荐写死在代码中，最好提供可配置，如 /uploadFiles/**
    @Value("${file.resourceHandler}")
    private String resourceHandler;

    @Value("${file.path}")
    private String filePath;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String coursePath = System.getProperty("user.dir") + File.separator + filePath + File.separator;
        Map<String,Object> data = FileUtils.formatPath(coursePath);
        String downloadPath = (String) data.get("downloadPath");
        String path = (String) data.get("path");
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }

        //就是说 url 中出现 resourceHandler 匹配时，则映射到 location 中去,location 相当于虚拟路径
        //映射本地文件时，开头必须是 file:/// 开头，表示协议
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("test/**").addResourceLocations("classpath:/img/");
        registry.addResourceHandler(resourceHandler).addResourceLocations("file:///" + downloadPath);
    }


}
