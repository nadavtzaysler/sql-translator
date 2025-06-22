package com.example.hql2sql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class TranslatorApplication {

    private static final Logger log = LoggerFactory.getLogger(TranslatorApplication.class);
    public static void main(String[] args) {
        log.info("starting translator application");
        SpringApplication.run(TranslatorApplication.class, args);
    }
}

