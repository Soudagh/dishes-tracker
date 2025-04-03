package org.example.testtask1221.service.user;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.example.testtask1221.BaseUnitTest;
import org.example.testtask1221.exception.EntityNotFoundException;
import org.example.testtask1221.model.GoalType;
import org.example.testtask1221.model.User;
import org.example.testtask1221.repository.UserRepository;
import org.example.testtask1221.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest extends BaseUnitTest {

  @InjectMocks
  private UserService userService;

  @Mock
  private UserRepository userRepository;


  @Test
  void findById_shouldThrowException_whenSelectedIdNotFound() {
    var userId = 1L;
    when(userRepository.findById(userId)).thenReturn(Optional.empty());
    assertThrows(EntityNotFoundException.class, () -> userService.get(userId));
  }

  @Test
  void findById_shouldNotThrowException_whenSelectedIdExists() {
    var user = getMockObject(User.class);
    var userId = user.getId();
    when(userRepository.findById(userId)).thenReturn(Optional.of(user));
    assertDoesNotThrow(() -> userService.get(userId));
  }

  @Test
  void create_shouldCreateUser() {
    var user = getMockObject(User.class);
    when(userRepository.save(user)).thenReturn(user);
    var userEntity = userService.create(user);
    assertNotNull(userEntity);
  }

  @Test
  void calculateCalorie_withNormMaintaining() {
    var user = getMockObject(User.class);
    user.setGoal(GoalType.WEIGHT_MAINTAINING).setHeight(170).setWeight(65).setAge(25);
    when(userRepository.save(user)).thenReturn(user);
    var userEntity = userService.create(user);
    assertNotNull(userEntity);
    var baseNorm = 10 * 65 + 6.25 * 170 - 5 * 25;
    assertEquals(baseNorm, userEntity.getDailyCalorieNorm(), 0.01);
  }

  @Test
  void calculateCalorie_withLossWeight() {
    var user = getMockObject(User.class);
    user.setGoal(GoalType.WEIGHT_LOSS).setHeight(180).setWeight(70).setAge(30);
    when(userRepository.save(user)).thenReturn(user);
    var userEntity = userService.create(user);
    assertNotNull(userEntity);
    var baseNorm = 10 * 70 + 6.25 * 180 - 5 * 30;
    assertEquals(baseNorm - 300, userEntity.getDailyCalorieNorm(), 0.01);
  }

  @Test
  void calculateCalorie_withWeightGain() {
    var user = getMockObject(User.class);
    user.setGoal(GoalType.WEIGHT_GAIN).setHeight(160).setWeight(60).setAge(20);
    when(userRepository.save(user)).thenReturn(user);
    var userEntity = userService.create(user);
    assertNotNull(userEntity);
    var baseNorm = 10 * 60 + 6.25 * 160 - 5 * 20;
    assertEquals(baseNorm + 300, userEntity.getDailyCalorieNorm(), 0.01);
  }

  @Test
  void calculateCalorie_withBoundaryValues() {
    var user = getMockObject(User.class);
    user.setGoal(GoalType.WEIGHT_MAINTAINING).setHeight(0).setWeight(0).setAge(0);
    when(userRepository.save(user)).thenReturn(user);
    var userEntity = userService.create(user);
    assertNotNull(userEntity);
    assertEquals(0.0, userEntity.getDailyCalorieNorm(), 0.01);
  }
}
