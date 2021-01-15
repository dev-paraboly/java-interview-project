package com.parabol.interview.mockproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import java.io.IOException;

@SpringBootApplication
@EnableAsync
public class MockProjectApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(MockProjectApplication.class, args);
    }
}
