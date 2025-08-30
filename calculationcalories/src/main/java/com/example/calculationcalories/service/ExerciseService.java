package com.example.calculationcalories.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.example.calculationcalories.entity.ExerciseRecord;
import com.example.calculationcalories.repository.ExerciseRecordRepository;

@Service
public class ExerciseService {

    private final ExerciseRecordRepository recordRepository;

    public ExerciseService(ExerciseRecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    /** 最新の記録を取得 */
    public ExerciseRecord getLatestRecord() {
        return recordRepository.findTopByOrderByDateDesc();
    }

    /** 指定日の消費カロリー合計を取得 */
    public double getTotalCaloriesByDate(LocalDate date) {
        Double total = recordRepository.sumCaloriesByDate(date);
        return total != null ? total : 0;
    }
}
