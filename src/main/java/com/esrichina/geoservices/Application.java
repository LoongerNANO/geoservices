package com.esrichina.geoservices;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * <p>
 * 启动器
 * </p>
 *
 * @description: 启动器
 * @author: LOONGER CHEN
 * @date: Created in 2020-05-25
 * @version: V1.0
 */

@EnableTransactionManagement
@EnableSwagger2Doc
@SpringBootApplication
@MapperScan(basePackages = "com.esrichina.geoservices.mapper")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
