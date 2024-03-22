package com.talentreef.interviewquestions.takehome.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;
import java.time.LocalDate;
@Entity
@Table(name = "Widget", schema = "dbo")
@SQLDelete(sql = "UPDATE Widget SET is_active = false WHERE id=?")
@Where(clause = "is_active=1")
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

    @Column(name = "insert_date")
    public LocalDate insertDate;

    @Column(name = "update_date")
    public LocalDate  updateDate;

    @Column( columnDefinition = "BIT DEFAULT 1",name = "is_active")
    public boolean isActive;
}
