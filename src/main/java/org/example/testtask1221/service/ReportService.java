package org.example.testtask1221.service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.example.testtask1221.model.DailyReport;
import org.example.testtask1221.model.Dish;
import org.example.testtask1221.model.Meal;
import org.example.testtask1221.model.MealsReport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReportService {

  private final MealService mealService;

  private final UserService userService;

  @Transactional
  public DailyReport dailyReport(Long userId, String date) {
    var dishes = getUserDishesForDate(userId, date);
    var calorySum = calculateCalories(dishes);
    return new DailyReport()
        .setCalorySum(calorySum)
        .setDishes(dishes);
  }

  @Transactional
  public boolean isInDailyNorm(Long userId, String date) {
    var user = userService.get(userId);
    var calorySum = calculateCalories(getUserDishesForDate(userId, date));
    return user.getDailyCalorieNorm() >= calorySum;
  }

  @Transactional
  public MealsReport dailyMealReport(Long userId) {
    var mealsByDate = getUserMeals(userId).stream()
        .collect(Collectors.groupingBy(meal -> meal.getDate().toString()));

    var dishesByDate = new HashMap<String, List<Dish>>();

    mealsByDate.forEach((date, meals) -> {
      var allDishes = meals.stream()
          .flatMap(meal -> meal.getDishes().stream())
          .toList();
      dishesByDate.put(date, allDishes);
    });

    return new MealsReport().setDishes(dishesByDate);
  }

  private List<Meal> getUserMeals(Long userId) {
    return mealService.getAll().stream()
        .filter(meal -> meal.getUser().getId().equals(userId))
        .toList();
  }

  private List<Dish> getUserDishesForDate(Long userId, String date) {
    return getUserMeals(userId).stream()
        .filter(meal -> meal.getDate().toString().equals(date))
        .flatMap(meal -> meal.getDishes().stream())
        .toList();
  }

  private double calculateCalories(List<Dish> dishes) {
    return dishes.stream()
        .mapToDouble(Dish::getCalories)
        .sum();
  }
}
