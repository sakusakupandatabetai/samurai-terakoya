package com.example.calculationcalories.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ExerciseLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 実施日 */
    private LocalDate date;

    /** 運動時間（時間単位） */
    private double duration;

    /** 消費カロリー */
    private double calories;

    /** ユーザー（多対一） */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /** 運動種目（多対一） */
    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;
}
