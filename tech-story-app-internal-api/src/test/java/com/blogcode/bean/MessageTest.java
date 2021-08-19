package com.blogcode.bean;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MessageTest {

    @Value("${msg.front-url}")
    String frontURI;

    @Test
    @DisplayName("메세지 프로퍼티 가져오기")
    @Disabled
    public void getMessage() {

        Assertions.assertThat(frontURI).isEqualTo("localhost:8080");
    }
}
