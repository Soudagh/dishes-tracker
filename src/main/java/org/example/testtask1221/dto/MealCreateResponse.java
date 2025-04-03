package org.example.testtask1221.dto;

import java.time.LocalDate;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class MealCreateResponse {

  private Long id;

  private LocalDate date;

  private Long user;

  private List<Long> dishes;
}
