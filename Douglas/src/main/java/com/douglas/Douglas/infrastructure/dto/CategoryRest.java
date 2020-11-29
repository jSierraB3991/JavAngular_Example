package com.douglas.Douglas.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class CategoryRest extends  BaseRest
{
    @NotBlank(message = "The name in category is mandatory")
    @Max(value = 30, message = "The maximum character for name of category is 30 characters")
    private String name;

    public CategoryRest(int id, String name)
    {
        setId(id);
        this.name = name;
    }
}
