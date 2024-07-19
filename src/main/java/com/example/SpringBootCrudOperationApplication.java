package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Azizbek Toshpulatov
 */
@SpringBootApplication
@EnableScheduling
public class SpringBootCrudOperationApplication {
    public static void main(String[] args)
    {
        SpringApplication.run(SpringBootCrudOperationApplication.class, args);
    }
}
