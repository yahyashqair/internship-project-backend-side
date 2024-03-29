package com.exult.ProjectCisco.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
public@Data
class FeatureXde {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn()
    //Json Ignore
    @JsonIgnore
    private Feature feature;
    @ManyToOne
    @JoinColumn
    private Xde xde ;
    private String typeOfRelation ;
}
