package com.example.limupashope;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.example.limupashope.config.StorageProperties;
import com.example.limupashope.service.StorageService;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class) //Add config storage vao he thong
public class LimupaShopEApplication {

    public static void main(String[] args) {
        SpringApplication.run(LimupaShopEApplication.class, args);
    }

    //co cai nay de khoi tao storage
    @Bean  //@Bean : để giúp cho IoC (Inversion of Container) có thể nạp và quản lý các thể hiện của lớp
    CommandLineRunner init(StorageService storageService) {
    	return (args -> {
    		storageService.init();
    	});
    }
    
}
