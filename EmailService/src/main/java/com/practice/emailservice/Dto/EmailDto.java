package com.practice.emailservice.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailDto {

    String to;
    String from;
    String subject;
    String body;
}
