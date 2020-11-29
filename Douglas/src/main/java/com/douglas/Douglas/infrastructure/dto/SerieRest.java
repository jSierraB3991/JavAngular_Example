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
public class SerieRest extends  BaseRest {

    @NotBlank(message = "The name in serie is mandatory")
    @Max(value = 30, message = "The maximum character for name of serie is 30 characters")
    private String name;

    @Max(value = 1000, message = "The maximum character for remarks is 1000 characters")
    private String remarks;

    private CategoryRest category;

    private List<SeasonRest> seasons;

    private List<String> images;

    public SerieRest(int id, String name, String remarks,
                     CategoryRest category, List<SeasonRest> seasons,
                     List<String> images){
        setId(id);
        this.name = name;
        this.remarks = remarks;
        this.category = category;
        this.seasons = seasons;
        this.images = images;
    }
}
