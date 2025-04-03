package org.example.testtask1221.model;

import java.util.List;
import java.util.Map;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class MealsReport {

  private Map<String, List<Dish>> dishes;
}
