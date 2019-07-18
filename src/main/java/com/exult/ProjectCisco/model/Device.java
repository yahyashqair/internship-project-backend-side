package com.exult.ProjectCisco.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public @Data
class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // ipAddress , cliAddress , username , password , protocol , port , snmp community
    @ManyToMany
    private Set<Configuration> configurationSet = new HashSet<Configuration>();
}
