package com.douglas.Douglas.infrastructure.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class VideoRest {
    private Integer id;
    private String urlVideo;
}
