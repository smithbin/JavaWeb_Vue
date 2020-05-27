package com.javaweb.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 设置虚拟路径，访问绝对路径下资源
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    @Value("${file.staticAccessPath}")
    private String staticAccessPath;
    @Value("${file.uploadFolder}")
    private String uploadFolder;

    /**
     * 注册静态文件的自定义映射路径
     *
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
        // 定义到新文件夹
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
        // 定义到指定目录
        registry.addResourceHandler(staticAccessPath)
                .addResourceLocations("file:" + uploadFolder);
    }
}
