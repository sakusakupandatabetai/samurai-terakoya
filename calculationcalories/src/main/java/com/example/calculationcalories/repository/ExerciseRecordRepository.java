package com.example.calculationcalories.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.calculationcalories.entity.ExerciseRecord;

public interface ExerciseRecordRepository extends JpaRepository<ExerciseRecord, Long> {

    // 最新の記録1件を取得（最新の日付順）
    ExerciseRecord findTopByOrderByDateDesc();

    // 指定日の消費カロリー合計を取得（合計がnullの場合は0を返す）
    @Query("SELECT COALESCE(SUM(e.calories), 0) FROM ExerciseRecord e WHERE e.date = :date")
    Double sumCaloriesByDate(@Param("date") LocalDate date);
}
