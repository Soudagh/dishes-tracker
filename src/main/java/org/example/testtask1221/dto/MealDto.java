package org.example.testtask1221.dto;

import jakarta.validation.constraints.PositiveOrZero;
import java.time.LocalDate;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class MealDto {

  @PositiveOrZero(message = "Id cannot be negative")
  private Long id;

  private LocalDate date;

  @PositiveOrZero(message = "User id cannot be negative")
  private Long user;

  private List<Long> dishes;
}
