package com.douglas.Douglas.infrastructure.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SeasonRest extends  BaseRest
{
    @NotBlank(message = "The name in season is mandatory")
    @Max(value = 30, message = "The maximum character for name of season is 30 characters")
    private String name;

    @Max(value = 1000, message = "The maximum character for remarks is 1000 characters")
    private String remarks;

    private List<String> videosUrl;

    public SeasonRest(int id, String name, String remarks,
                      List<String> videosUrl) {
        setId(id);
        this.name = name;
        this.remarks = remarks;
        this.videosUrl = videosUrl;
    }
}
