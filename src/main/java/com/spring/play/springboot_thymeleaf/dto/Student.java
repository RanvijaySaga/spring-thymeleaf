package com.spring.play.springboot_thymeleaf.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Student {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private int classNumber;
    private String section;

}
