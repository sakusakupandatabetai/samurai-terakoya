package com.example.calculationcalories.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ExerciseRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private String exerciseName;
    private double calories;

    public ExerciseRecord() {}

    // 3引数コンストラクタを追加（これが必要です）
    public ExerciseRecord(LocalDate date, String exerciseName, double calories) {
        this.date = date;
        this.exerciseName = exerciseName;
        this.calories = calories;
    }

    // getter / setter
    public Long getId() { return id; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getExerciseName() { return exerciseName; }
    public void setExerciseName(String exerciseName) { this.exerciseName = exerciseName; }

    public double getCalories() { return calories; }
    public void setCalories(double calories) { this.calories = calories; }
}
