package com.example.calculationcalories.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.calculationcalories.entity.ExerciseRecord;
import com.example.calculationcalories.entity.User;
import com.example.calculationcalories.repository.UserRepository;
import com.example.calculationcalories.service.ExerciseService;

@Controller
public class HomeController {

    private final ExerciseService exerciseService;
    private final UserRepository userRepository;

    public HomeController(ExerciseService exerciseService, UserRepository userRepository) {
        this.exerciseService = exerciseService;
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        // 最新運動記録
        ExerciseRecord latestRecord = exerciseService.getLatestRecord();
        if (latestRecord != null) {
            model.addAttribute("exerciseName", latestRecord.getExerciseName());
            model.addAttribute("calories", latestRecord.getCalories());
            model.addAttribute("date", latestRecord.getDate().format(DateTimeFormatter.ofPattern("yyyy年MM月dd日")));
        } else {
            model.addAttribute("exerciseName", "記録なし");
            model.addAttribute("calories", 0);
            model.addAttribute("date", "");
        }

        // 今日の合計消費カロリー
        double totalCalories = exerciseService.getTotalCaloriesByDate(LocalDate.now());
        model.addAttribute("totalCalories", totalCalories);

        // ユーザー情報
        User user = userRepository.findById(1L).orElse(new User());
        model.addAttribute("user", user);

        return "index"; // index.html にプロフィール情報を表示
    }
}
