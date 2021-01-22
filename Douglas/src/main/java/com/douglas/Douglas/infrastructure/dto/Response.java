package com.douglas.Douglas.infrastructure.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Response {
    private boolean isSuccess;
    private Object message;
}
