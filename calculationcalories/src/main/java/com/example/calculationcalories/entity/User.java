package com.example.calculationcalories.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double weight;
    private Double height;
    private Double dailyCalories;

    public User() {}

    // getter/setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Double getWeight() { return weight; }
    public void setWeight(Double weight) { this.weight = weight; }

    public Double getHeight() { return height; }
    public void setHeight(Double height) { this.height = height; }

    public Double getDailyCalories() { return dailyCalories; }
    public void setDailyCalories(Double dailyCalories) { this.dailyCalories = dailyCalories; }
    
    public void calculateDailyCalories() {
        if(weight != null && height != null) {
            double bmr = 22 * Math.pow(height / 100.0, 2);
            this.dailyCalories = bmr * 35;  // 仮の計算式
        }
    }
}
