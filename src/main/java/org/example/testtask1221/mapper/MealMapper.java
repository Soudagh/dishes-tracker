package org.example.testtask1221.mapper;

import org.example.testtask1221.dto.MealDto;
import org.example.testtask1221.dto.MealCreateResponse;
import org.example.testtask1221.model.Meal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class, DishMapper.class})
public interface MealMapper {

  Meal dtoToMeal(MealDto mealDto);

  @Mapping(target = "user", source = "meal.user.id")
  MealCreateResponse mealToDtoResponse(Meal meal);
}
