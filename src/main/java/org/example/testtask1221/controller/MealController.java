package org.example.testtask1221.controller;

import lombok.RequiredArgsConstructor;
import org.example.testtask1221.dto.MealDto;
import org.example.testtask1221.dto.MealCreateResponse;
import org.example.testtask1221.mapper.MealMapper;
import org.example.testtask1221.model.Meal;
import org.example.testtask1221.service.MealService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/meals")
@RequiredArgsConstructor
public class MealController {

  private final MealService mealService;

  private final MealMapper mealMapper;

  @GetMapping("/{id}")
  ResponseEntity<Meal> get(@PathVariable Long id) {
    return ResponseEntity.ok(mealService.get(id));
  }

  @PostMapping
  ResponseEntity<MealCreateResponse> create(@RequestBody MealDto userDto) {
    var meal = mealService.create(mealMapper.dtoToMeal(userDto));
    return ResponseEntity.status(HttpStatus.CREATED).body(mealMapper.mealToDtoResponse(meal));
  }
}
