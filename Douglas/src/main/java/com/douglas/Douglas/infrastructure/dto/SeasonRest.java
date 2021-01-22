package com.douglas.Douglas.infrastructure.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SeasonRest extends  BaseRest
{
    @NotBlank(message = "The name in season is mandatory")
    @Size(max = 100, min = 1, message = "The maximum character for name of season is 100 characters")
    private String name;

    @Size(max = 2000, min = 1, message = "The maximum character for remarks is 2000 characters")
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
