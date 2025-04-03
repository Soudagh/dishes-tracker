package org.example.testtask1221.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;
import org.example.testtask1221.model.GoalType;

@Data
@Accessors(chain = true)
public class UserDto {

  @PositiveOrZero(message = "Id cannot be negative")
  private Long id;

  @NotBlank(message = "Name cannot be empty")
  @Size(max = 50, message = "Name must be at most 50 characters")
  private String name;

  @Email(message = "Invalid email format", regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
  @NotEmpty(message = "Email cannot be empty")
  @Size(max = 254)
  private String email;

  @Min(value = 13, message = "Age cannot be less than 13")
  @Max(value = 125, message = "Age cannot be more than 125")
  private int age;

  @Min(value = 100, message = "Height cannot be less than 100")
  @Max(value = 230, message = "Height cannot be more than 230")
  private int height;

  @Min(value = 20, message = "Weight cannot be less than 20")
  @Max(value = 550, message = "Weight cannot be more than 550")
  private int weight;

  private GoalType goal;
}
