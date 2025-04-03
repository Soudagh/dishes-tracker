package org.example.testtask1221.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;
import org.example.testtask1221.model.GoalType;

@Data
@Accessors(chain = true)
public class DishDto {

  @PositiveOrZero(message = "Id cannot be negative")
  private Long id;

  @NotBlank(message = "Title cannot be empty")
  @Size(max = 50, message = "Title must be at most 50 characters")
  private String title;

  @PositiveOrZero(message = "Calories cannot be negative")
  private double calories;

  @PositiveOrZero(message = "Proteins cannot be negative")
  private double proteins;

  @PositiveOrZero(message = "Fats cannot be negative")
  private double fats;

  @PositiveOrZero(message = "Carbohydrates cannot be negative")
  private int carbohydrates;
}
