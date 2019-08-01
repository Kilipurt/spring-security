package com.findme.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ROLE")
@Getter
@Setter
public class Role {

    @Id
    @SequenceGenerator(name = "ROLE_SEQ", sequenceName = "ROLE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROLE_SEQ")
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;
}
