package org.example.testtask1221.service.user;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.example.testtask1221.BaseUnitTest;
import org.example.testtask1221.exception.EntityNotFoundException;
import org.example.testtask1221.model.Dish;
import org.example.testtask1221.repository.DishRepository;
import org.example.testtask1221.service.DishService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DishServiceTest extends BaseUnitTest {

  @InjectMocks
  private DishService dishService;

  @Mock
  private DishRepository dishRepository;


  @Test
  void findById_shouldThrowException_whenSelectedIdNotFound() {
    var dishId = 1L;
    when(dishRepository.findById(dishId)).thenReturn(Optional.empty());
    assertThrows(EntityNotFoundException.class, () -> dishService.get(dishId));
  }

  @Test
  void findById_shouldNotThrowException_whenSelectedIdExists() {
    var dish = getMockObject(Dish.class);
    var dishId = dish.getId();
    when(dishRepository.findById(dishId)).thenReturn(Optional.of(dish));
    assertDoesNotThrow(() -> dishService.get(dishId));
  }

  @Test
  void create_shouldCreateDish() {
    var dish = getMockObject(Dish.class);
    when(dishRepository.save(dish)).thenReturn(dish);
    var dishEntity = dishService.create(dish);
    assertNotNull(dishEntity);
  }
}
