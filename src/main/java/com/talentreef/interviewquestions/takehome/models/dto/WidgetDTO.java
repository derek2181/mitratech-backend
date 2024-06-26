package com.talentreef.interviewquestions.takehome.models.dto;

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
@Builder
@AllArgsConstructor
public class WidgetDTO {
  @Size(min = 3, max = 100)
  @NotNull
  @NotBlank
  private String name;

  @Size(min = 5, max = 1000)
  @NotBlank
  @NotNull
  private String description;

  @Digits(integer = 5, fraction = 2)

  @DecimalMin(value = "1",message = "This field must greater than 1")
  @DecimalMax(value = "20000",message = "This field must be less than 20000")
  @NotNull
  private BigDecimal price;

}
