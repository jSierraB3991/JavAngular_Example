package com.douglas.Douglas.core.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "video_season")
public class VideoSeason extends  BaseEntity
{
    @Column(name = "url_video", length = 255)
    private String urlVideo;

    @ManyToOne(targetEntity = Season.class)
    private Season season;

    public VideoSeason(int id, String urlVideo, Season season){
        setId(id);
        this.urlVideo = urlVideo;
        this.season = season;
    }
}
