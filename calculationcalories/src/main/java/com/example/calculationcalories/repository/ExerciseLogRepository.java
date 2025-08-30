package com.example.calculationcalories.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.calculationcalories.entity.ExerciseLog;

@Repository
public interface ExerciseLogRepository extends JpaRepository<ExerciseLog, Long> {

    /** ユーザーごとのログ一覧 */
    List<ExerciseLog> findByUserIdOrderByDateDesc(Long userId);

    /** 日別カロリー集計 */
    @Query("SELECT e.date, SUM(e.calories) FROM ExerciseLog e GROUP BY e.date ORDER BY e.date")
    List<Object[]> sumCaloriesByDate();

    /** 運動種目別カロリー割合 */
    @Query("SELECT e.exercise.name, SUM(e.calories) FROM ExerciseLog e GROUP BY e.exercise.name")
    List<Object[]> sumCaloriesByExercise();
}
