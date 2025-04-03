package org.example.testtask1221.service;

import lombok.RequiredArgsConstructor;
import org.example.testtask1221.exception.EntityExistsException;
import org.example.testtask1221.exception.EntityNotFoundException;
import org.example.testtask1221.model.Dish;
import org.example.testtask1221.repository.DishRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DishService {

  private final DishRepository dishRepository;

  public Dish get(Long id) {
    return dishRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Dish.class, id));
  }

  public Dish create(Dish dish) {
    if (dishRepository.existsById(dish.getId())) throw new EntityExistsException(Dish.class, dish.getId().toString());
    return dishRepository.save(dish);
  }
}
