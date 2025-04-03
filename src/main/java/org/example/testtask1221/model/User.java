package org.example.testtask1221.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "users")
public class User {

  @Id
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "email", nullable = false)
  private String email;

  @Column(name = "age", nullable = false)
  private int age;

  @Column(name = "height", nullable = false)
  private int height;

  @Column(name = "weight", nullable = false)
  private int weight;

  @Column(name = "goal", nullable = false)
  private GoalType goal;

  @Column(name = "dailyCalorieNorm", nullable = false)
  private double dailyCalorieNorm;
}
