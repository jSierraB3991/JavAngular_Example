package com.douglas.Douglas.infrastructure.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SerieRest extends  BaseRest {

    @NotNull(message = "The name in serie is mandatory")
    @NotBlank(message = "The name in serie is mandatory")
    @Size(max = 100, message = "The maximum character for name of serie is 100 characters")
    private String name;

    @Size(max = 2000, message = "The maximum character for remarks is 2000 characters")
    private String remarks;

    private CategoryRest category;

    private List<SeasonRest> seasons;

    private List<String> images;

    private String firstImage;

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
