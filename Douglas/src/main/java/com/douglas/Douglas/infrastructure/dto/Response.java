package com.douglas.Douglas.infrastructure.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class Response {

    private boolean isSuccess;
    private Object message;
}
