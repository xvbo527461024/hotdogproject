package cn.hot.hotdog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = "cn.hot.hotdog.client")
public class ProductApplication_8002 {
    public static void main(String[] args) {
        SpringApplication.run(ProductApplication_8002.class);
    }
}
