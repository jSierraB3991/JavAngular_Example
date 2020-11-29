package com.douglas.Douglas.core.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "serie")
public class Serie extends BaseEntity
{
    @Column(unique = true, name = "name", length = 100, nullable = false)
    private String name;

    @Column(length = 1000, name = "remarks")
    private String remarks;

    @ManyToOne(targetEntity = Category.class)
    private Category category;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,targetEntity = Season.class)
    private List<Season> seasons;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,targetEntity = ImageSerie.class)
    private List<ImageSerie> imageSeries;


    public Serie(int id, String name, String remarks,
                 Category category, List<Season> seasons,
                 List<ImageSerie> imageSeries){
        setId(id);
        this.name = name;
        this.remarks = remarks;
        this.category = category;
        this.seasons = seasons;
        this.imageSeries = imageSeries;
    }
}
