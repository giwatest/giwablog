package com.giwa.blog.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

@ComponentScan("com.giwa")
@SpringBootApplication
public class BlogApplication {

    private static final Logger LOG = LoggerFactory.getLogger(BlogApplication.class);

    public static void main(String[] args) {
        //SpringApplication.run(BlogApplication.class, args);
        SpringApplication app = new SpringApplication(BlogApplication.class);
        Environment env = app.run(args).getEnvironment();
        LOG.info("启动成功！！");
        LOG.info("地址：\thttp://127.0.0.1:{}", env.getProperty("server.port"));
    }


}
