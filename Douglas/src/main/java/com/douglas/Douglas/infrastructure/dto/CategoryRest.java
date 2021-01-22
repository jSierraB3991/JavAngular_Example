package com.douglas.Douglas.infrastructure.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class CategoryRest extends  BaseRest
{
    @NotNull(message = "The name in category is mandatory")
    @NotBlank(message = "The name in category is mandatory")
    @Size(max = 30, message = "The maximum character for name of category is 30 characters")
    private String name;

    public CategoryRest(int id, String name)
    {
        setId(id);
        this.name = name;
    }
}
