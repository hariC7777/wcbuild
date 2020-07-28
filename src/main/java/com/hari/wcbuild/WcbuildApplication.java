package com.hari.wcbuild;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hari.wcbuild.mapper")
public class WcbuildApplication {

    public static void main(String[] args) {
        SpringApplication.run(WcbuildApplication.class, args);
    }

}
