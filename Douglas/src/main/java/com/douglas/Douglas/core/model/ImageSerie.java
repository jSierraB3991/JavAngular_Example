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
@Table(name = "image_serie")
public class ImageSerie extends  BaseEntity
{
    @Column(name = "url_image", length = 255)
    private String urlImage;

    @ManyToOne(targetEntity = Serie.class)
    private Serie serie;

    public ImageSerie(int id, String urlImage, Serie serie){
        setId(id);
        this.urlImage = urlImage;
        this.serie = serie;
    }
}
