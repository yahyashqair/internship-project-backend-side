package com.exult.ProjectCisco.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

//@Embeddable
@Entity
@Data
public class Criteria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @Column(name="name")
    private String name;
    @Column(name="operator")
    private String operator;
    @ElementCollection
    @CollectionTable(
            joinColumns=@JoinColumn(name="criteria_id")
    )
    private Set<Configuration> configurationSet= new HashSet<Configuration>();
}
