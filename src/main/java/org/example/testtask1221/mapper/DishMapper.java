package org.example.testtask1221.mapper;

import java.util.List;
import org.example.testtask1221.dto.DishDto;
import org.example.testtask1221.model.Dish;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DishMapper {

  Dish dtoToDish(DishDto dishDto);

  Dish idToDish(Long id);

  default List<Dish> idsToDishes(List<Long> dishes) {
    return dishes.stream().map(this::idToDish).toList();
  }

  default List<Long> dishesToId(List<Dish> dishes) {
    return dishes.stream().map(Dish::getId).toList();
  }
}
