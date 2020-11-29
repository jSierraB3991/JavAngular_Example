package com.douglas.Douglas.core.model;

import com.douglas.Douglas.core.model.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor
public class Category extends BaseEntity
{
    @Column(unique = true, name = "name", length = 100, nullable = false)
    private String name;

    public Category(int id, String name){
        setId(id);
        this.name = name;
    }
}
