package org.example.testtask1221.service.meal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import org.example.testtask1221.BaseUnitTest;
import org.example.testtask1221.exception.EntityNotFoundException;
import org.example.testtask1221.model.Meal;
import org.example.testtask1221.repository.MealRepository;
import org.example.testtask1221.service.MealService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class MealServiceTest extends BaseUnitTest {

  @InjectMocks
  private MealService mealService;

  @Mock
  private MealRepository mealRepository;

  @Test
  void create_shouldCreateStudent() {
    var meal = getMockObject(Meal.class);
    when(mealRepository.save(meal)).thenReturn(meal);
    var mealEntity = assertDoesNotThrow(() -> mealService.create(meal));
    assertNotNull(mealEntity);
    assertNotNull(mealEntity.getId());
  }

  @Test
  void findById_shouldThrowException() {
    var mealId = 1L;
    when(mealRepository.findById(mealId)).thenReturn(Optional.empty());
    assertThrows(EntityNotFoundException.class, () -> mealService.get(mealId));
  }

  @Test
  void findById_shouldReturnStudent() {
    var meal = getMockObject(Meal.class);
    when(mealRepository.findById(meal.getId())).thenReturn(Optional.of(meal));
    assertDoesNotThrow(() -> mealService.get(meal.getId()));
  }

  @Test
  void findAll_shouldReturnListOfMeals() {
    var meal1 = getMockObject(Meal.class);
    var meal2 = getMockObject(Meal.class);
    var meals = List.of(meal1, meal2);
    when(mealRepository.findAll()).thenReturn(meals);
    var resultList = mealService.getAll();
    assertNotNull(resultList);
    assertEquals(2, resultList.size());
    assertEquals(meal1, resultList.get(0));
    assertEquals(meal2, resultList.get(1));
  }
}