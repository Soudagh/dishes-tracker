package org.example.testtask1221.model;

import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class DailyReport {

  private double calorySum;

  private List<Dish> dishes;
}
