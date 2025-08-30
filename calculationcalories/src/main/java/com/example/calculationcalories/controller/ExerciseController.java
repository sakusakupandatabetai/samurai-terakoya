package com.example.calculationcalories.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.calculationcalories.entity.Exercise;
import com.example.calculationcalories.entity.ExerciseRecord;
import com.example.calculationcalories.repository.ExerciseRecordRepository;
import com.example.calculationcalories.repository.ExerciseRepository;

@Controller
@RequestMapping("/exercise")
public class ExerciseController {

    private final ExerciseRepository exerciseRepository;
    private final ExerciseRecordRepository recordRepository;

    public ExerciseController(ExerciseRepository exerciseRepository, ExerciseRecordRepository recordRepository) {
        this.exerciseRepository = exerciseRepository;
        this.recordRepository = recordRepository;
    }

    // /exercise または /exercise/ でアクセスされたら入力画面表示にリダイレクト
    @GetMapping("")
    public String redirectToInput() {
        return "redirect:/exercise/input";
    }

    // 入力画面表示
    @GetMapping("/input")
    public String showInputForm(Model model) {
        List<Exercise> exercises = exerciseRepository.findAll();
        model.addAttribute("exercises", exercises);
        return "exerciseform";  // exerciseform.htmlを表示
    }

    // カロリー計算・DB保存
    @PostMapping("")
    public String calculateCalories(
            @RequestParam Long exerciseId,
            @RequestParam double duration,
            @RequestParam double weight,
            Model model) {

        Exercise exercise = exerciseRepository.findById(exerciseId).orElseThrow();
        double calories = exercise.getMets() * weight * duration;

        model.addAttribute("exerciseName", exercise.getName());
        model.addAttribute("calories", calories);

        // 今日の日付で保存
        ExerciseRecord record = new ExerciseRecord(LocalDate.now(), exercise.getName(), calories);
        recordRepository.save(record);

        return "exerciseresult";  // 計算結果画面を表示
    }
}
