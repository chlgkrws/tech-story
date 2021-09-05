package com.blogcode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * <pre>
 * k-velog
 *
 * Description : 내부 module api spring boot starter
 * </pre>
 *
 * @author LeeJH
 * @since 2021-06-26
 */
@EnableJpaAuditing
@SpringBootApplication
public class OAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(OAuthApplication.class, args);
    }
}
