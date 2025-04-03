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
@Table(name = "dishes")
public class Dish {

  @Id
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "calories", nullable = false)
  private double calories;

  @Column(name = "proteins", nullable = false)
  private double proteins;

  @Column(name = "fats", nullable = false)
  private double fats;

  @Column(name = "carbohydrates", nullable = false)
  private int carbohydrates;
}

