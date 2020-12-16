package com.zhongsheng;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EducationApplication {

    private static final Logger log = LoggerFactory.getLogger(EducationApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(EducationApplication.class, args);
        log.info("系统已启动。。。");
    }

}


