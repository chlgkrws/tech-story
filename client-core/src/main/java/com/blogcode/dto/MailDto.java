package com.blogcode.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MailDto {
    private String emailAddress;
    private String title;
    private String message;
}
