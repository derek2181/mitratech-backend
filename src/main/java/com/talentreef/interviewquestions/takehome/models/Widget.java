package com.talentreef.interviewquestions.takehome.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@Table
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder(toBuilder=true)
public class Widget {

  @Column(length = 100, nullable = false, unique = true)
  @Size(min = 3, max = 100)
  @NotNull
  private String name;

  @Column(length = 1000, nullable = false, unique = true)
  @Size(min = 5, max = 1000)
  private String description;

  @Column(precision = 6, scale = 2)
  @DecimalMin(value = "1")
  @DecimalMax(value = "20000")
  @NotNull
  private BigDecimal price;
}
