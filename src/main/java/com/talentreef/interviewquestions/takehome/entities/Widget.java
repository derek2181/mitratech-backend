package com.talentreef.interviewquestions.takehome.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "Widget", schema = "dbo")
@Data
public class Widget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(length = 100, nullable = false, unique = true)
    public String name;

    @Column(length = 1000, nullable = false, unique = true)
    public String description;
    @Column(precision = 8, scale = 2) // Example precision and scale
    public BigDecimal price;
}
