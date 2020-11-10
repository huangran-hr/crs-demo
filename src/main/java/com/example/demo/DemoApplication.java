package com.example.demo;

import com.sjlh.hotel.crs.configurate.EnableCrsOrderClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableCrsOrderClient
@SpringBootApplication(scanBasePackages = {"com.example.demo", "com.sjlh.hotel"})
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
