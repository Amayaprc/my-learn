package com.prc.springbootshiro.shiro;

import com.prc.springbootshiro.utils.YamlPropertySourceFactory;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Data
@Component
@PropertySource(value = {"classpath:/config/shiro.yml"}, encoding = "utf-8", factory = YamlPropertySourceFactory.class)
@ConfigurationProperties(prefix = "permission-config")
public class PermsMap {

    private List<Map<String,String>> perms;
}
