package org.example.testtask1221.service;

import lombok.RequiredArgsConstructor;
import org.example.testtask1221.exception.EntityExistsException;
import org.example.testtask1221.exception.EntityNotFoundException;
import org.example.testtask1221.model.GoalType;
import org.example.testtask1221.model.User;
import org.example.testtask1221.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public User get(Long id) {
    return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(User.class, id));
  }

  public User create(User user) {
    if (userRepository.existsById(user.getId())) {
      throw new EntityExistsException(User.class, user.getId().toString());
    }
    user.setDailyCalorieNorm(calculateCalorieNorm(user.getHeight(), user.getWeight(), user.getAge(), user.getGoal()));
    return userRepository.save(user);
  }

  private double calculateCalorieNorm(int height, int weight, int age, GoalType goal) {
    var baseNorm = 10 * weight + 6.25 * height - 5 * age;
    return switch (goal) {
      case WEIGHT_LOSS -> baseNorm - 300;
      case WEIGHT_GAIN -> baseNorm + 300;
      default -> baseNorm;
    };
  }
}
