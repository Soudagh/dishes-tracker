package org.example.testtask1221.service;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.testtask1221.exception.EntityExistsException;
import org.example.testtask1221.exception.EntityNotFoundException;
import org.example.testtask1221.model.Meal;
import org.example.testtask1221.repository.MealRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MealService {

  private final MealRepository mealRepository;

  public Meal get(Long id) {
    return mealRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Meal.class, id));
  }

  public List<Meal> getAll() {
    List<Meal> meals = new ArrayList<>();
    mealRepository.findAll().forEach(meals::add);
    return meals;
  }

  @Transactional
  public Meal create(Meal meal) {
    if (meal.getId() != null && mealRepository.existsById(meal.getId())) {
      throw new EntityExistsException(Meal.class, meal.getId().toString());
    }
    return mealRepository.save(meal);
  }
}
