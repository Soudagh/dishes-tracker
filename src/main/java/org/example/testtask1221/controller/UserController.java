package org.example.testtask1221.controller;

import lombok.RequiredArgsConstructor;
import org.example.testtask1221.dto.UserDto;
import org.example.testtask1221.mapper.UserMapper;
import org.example.testtask1221.model.User;
import org.example.testtask1221.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  private final UserMapper userMapper;

  @GetMapping("/{id}")
  ResponseEntity<User> get(@PathVariable Long id) {
    return ResponseEntity.ok(userService.get(id));
  }

  @PostMapping
  ResponseEntity<User> create(@RequestBody UserDto userDto) {
    var user = userMapper.dtoToUser(userDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(user));
  }
}
