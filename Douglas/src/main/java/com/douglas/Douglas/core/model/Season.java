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
@Table(name = "season")
public class Season extends BaseEntity
{
    @Column(unique = true, name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "remarks", length = 1000)
    private String remarks;

    @ManyToOne(targetEntity = Serie.class, cascade = CascadeType.ALL)
    private Serie serie;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,targetEntity = VideoSeason.class)
    private List<VideoSeason> videoSeasons;

    public Season(int id, String name, String remarks, Serie serie,
                  List<VideoSeason> videoSeasons){
        setId(id);
        this.name = name;
        this.remarks = remarks;
        this.serie = serie;
        this.videoSeasons = videoSeasons;
    }
}
