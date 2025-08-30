package com.example.calculationcalories.controller;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.calculationcalories.entity.ExerciseRecord;
import com.example.calculationcalories.repository.ExerciseRecordRepository;

@Controller
public class StatsController {

    private final ExerciseRecordRepository recordRepository;

    public StatsController(ExerciseRecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    @GetMapping("/exercise/history")
    public String history(Model model) {

        List<ExerciseRecord> records = recordRepository.findAll();

        // 日付ごとに消費カロリー合計を集計 (yyyy/MM/dd形式)
        Map<String, Double> caloriesByDate = records.stream()
            .collect(Collectors.groupingBy(
                r -> r.getDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")),
                LinkedHashMap::new,
                Collectors.summingDouble(ExerciseRecord::getCalories)
            ));

        // 種目ごとに消費カロリー合計を集計
        Map<String, Double> caloriesByExercise = records.stream()
            .collect(Collectors.groupingBy(
                ExerciseRecord::getExerciseName,
                LinkedHashMap::new,
                Collectors.summingDouble(ExerciseRecord::getCalories)
            ));

        model.addAttribute("records", records);
        model.addAttribute("barLabels", new ArrayList<>(caloriesByDate.keySet()));
        model.addAttribute("barValues", new ArrayList<>(caloriesByDate.values()));
        model.addAttribute("pieLabels", new ArrayList<>(caloriesByExercise.keySet()));
        model.addAttribute("pieValues", new ArrayList<>(caloriesByExercise.values()));

        return "history";
    }

    // 個別削除
    @PostMapping("/exercise/history/deleteOne")
    public String deleteOne(@RequestParam Long id) {
        recordRepository.deleteById(id);
        return "redirect:/exercise/history";
    }

    // 全削除
    @PostMapping("/exercise/history/deleteAll")
    public String deleteAll() {
        recordRepository.deleteAll();
        return "redirect:/exercise/history";
    }
}
