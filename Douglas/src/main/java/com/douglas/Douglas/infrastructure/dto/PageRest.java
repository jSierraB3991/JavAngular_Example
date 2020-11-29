package com.douglas.Douglas.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageRest<T>
{
    private List<T> data;
    private Integer previousPage;
    private Integer maxPage;
    private Integer nextPage;
}
