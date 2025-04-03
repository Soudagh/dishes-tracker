package org.example.testtask1221.service.report;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import org.example.testtask1221.BaseUnitTest;
import org.example.testtask1221.model.Dish;
import org.example.testtask1221.model.Meal;
import org.example.testtask1221.model.User;
import org.example.testtask1221.service.MealService;
import org.example.testtask1221.service.ReportService;
import org.example.testtask1221.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ReportServiceTest extends BaseUnitTest {

  @InjectMocks
  private ReportService reportService;

  @Mock
  private MealService mealService;

  @Mock
  private UserService userService;

  private final Long userId = 1L;
  private final String date = "2025-04-03";

  private final Dish dish1 = getMockObject(Dish.class).setId(1L).setCalories(300);
  private final Dish dish2 = getMockObject(Dish.class).setId(2L).setCalories(200);

  private final User mockUser = new User().setId(userId).setDailyCalorieNorm(600);

  private final Meal meal1 = getMockObject(Meal.class)
      .setId(1L)
      .setDate(LocalDate.parse("2025-04-03"))
      .setUser(mockUser)
      .setDishes(List.of(dish1, dish2));

  private final Meal meal2 = getMockObject(Meal.class)
      .setId(2L)
      .setDate(LocalDate.parse("2025-04-04"))
      .setUser(mockUser)
      .setDishes(List.of(dish1));

  @Test
  void dailyReport_shouldReturnMeals() {
    when(mealService.getAll()).thenReturn(List.of(meal1, meal2));
    var report = reportService.dailyReport(userId, date);
    assertEquals(500, report.getCalorySum());
    assertEquals(2, report.getDishes().size());
  }

  @Test
  void isInDailyNorm_shouldReturnTrue() {
    when(mealService.getAll()).thenReturn(List.of(meal1));
    when(userService.get(userId)).thenReturn(mockUser);
    boolean result = reportService.isInDailyNorm(userId, date);
    assertTrue(result);
  }

  @Test
  void isInDailyNorm_shouldReturnFalse() {
    mockUser.setDailyCalorieNorm(400);
    when(mealService.getAll()).thenReturn(List.of(meal1));
    when(userService.get(userId)).thenReturn(mockUser);
    boolean result = reportService.isInDailyNorm(userId, date);
    assertFalse(result);
  }

  @Test
  void dailyMealReport_shouldReturnMeals() {
    when(mealService.getAll()).thenReturn(List.of(meal1, meal2));
    var report = reportService.dailyMealReport(userId);
    assertEquals(2, report.getDishes().size());
    assertEquals(2, report.getDishes().get("2025-04-03").size());
    assertEquals(1, report.getDishes().get("2025-04-04").size());
  }
}
