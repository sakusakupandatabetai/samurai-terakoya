package com.example.calculationcalories.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.calculationcalories.entity.User;
import com.example.calculationcalories.repository.UserRepository;

@Controller
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // GETリクエストでプロフィール画面を表示
    @GetMapping("/user/profile")
    public String profile(Model model) {
        User user = userRepository.findById(1L).orElse(new User());
        System.out.println("ユーザー名：" + user.getName()); // ログ確認用

        model.addAttribute("username", user.getName());
        model.addAttribute("user", user);
        return "profile";
    }

    // POSTリクエストでプロフィール保存し、リダイレクトでGETへ戻る
    @PostMapping("/user/profile/save")
    public String saveProfile(@ModelAttribute("user") User user) {
        if (user.getWeight() != null && user.getHeight() != null) {
            double bmr = 22 * Math.pow(user.getHeight() / 100, 2);
            double dailyCalories = bmr * 35;
            long roundedCalories = Math.round(dailyCalories);
            user.setDailyCalories((double) roundedCalories);
        }

        userRepository.save(user);

        // 保存後はリダイレクトしてGET処理へ
        return "redirect:/user/profile";
    }
}
