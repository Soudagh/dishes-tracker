package org.example.testtask1221.controller;

import lombok.RequiredArgsConstructor;
import org.example.testtask1221.dto.DishDto;
import org.example.testtask1221.mapper.DishMapper;
import org.example.testtask1221.model.Dish;
import org.example.testtask1221.service.DishService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/dishes")
@RequiredArgsConstructor
public class DishController {

  private final DishService dishService;

  private final DishMapper dishMapper;

  @GetMapping("/{id}")
  ResponseEntity<Dish> get(@PathVariable Long id) {
    return ResponseEntity.ok(dishService.get(id));
  }

  @PostMapping
  ResponseEntity<Dish> create(@RequestBody DishDto userDto) {
    var user = dishMapper.dtoToDish(userDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(dishService.create(user));
  }
}
