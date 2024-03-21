package com.talentreef.interviewquestions.takehome.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@NoArgsConstructor
public class WidgetDTO {
  @Size(min = 3, max = 100)
  @NotNull
  private String name;

  @Size(min = 5, max = 1000)
  private String description;

  @Digits(integer = 5, fraction = 2)

  @DecimalMin(value = "1",message = "This field must greater than 1")
  @DecimalMax(value = "20000",message = "This field must be less than 20000")
  @NotNull
  private BigDecimal price;
}
