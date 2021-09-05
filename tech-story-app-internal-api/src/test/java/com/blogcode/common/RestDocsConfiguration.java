package com.blogcode.common;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.RestDocsMockMvcConfigurationCustomizer;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.ManualRestDocumentation;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentationConfigurer;
import org.springframework.restdocs.operation.preprocess.ContentModifyingOperationPreprocessor;
import org.springframework.restdocs.operation.preprocess.PrettyPrintingContentModifier;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@TestConfiguration
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
public class RestDocsConfiguration {

    @Bean
    public RestDocsMockMvcConfigurationCustomizer restDocsMockMvcConfigurationCustomizer(){
        return new RestDocsMockMvcConfigurationCustomizer() {
            @Override
            public void customize(MockMvcRestDocumentationConfigurer configurer) {
                configurer.operationPreprocessors()
                        .withRequestDefaults(new ContentModifyingOperationPreprocessor(new PrettyPrintingContentModifier()))
                        .withResponseDefaults(new ContentModifyingOperationPreprocessor(new PrettyPrintingContentModifier()))
                .and()
                    .uris()
                        .withHost("http")
                        .withHost("localhost")
                        .withPort(8084)
                ;
            }
        };
    }
}
