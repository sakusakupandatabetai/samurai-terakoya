package com.example.calculationcalories;

import org.springframework.stereotype.Component;

import com.example.calculationcalories.entity.Exercise;
import com.example.calculationcalories.repository.ExerciseRepository;

import jakarta.annotation.PostConstruct;

@Component
public class DataLoader {

    private final ExerciseRepository exerciseRepository;

    public DataLoader(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @PostConstruct
    public void loadData() {
        if (exerciseRepository.count() == 0) { // データがなければ初期登録
            exerciseRepository.save(new Exercise("ウォーキング", 3.5));
            exerciseRepository.save(new Exercise("ジョギング", 7.0));
            exerciseRepository.save(new Exercise("サイクリング", 6.8));
            exerciseRepository.save(new Exercise("水泳", 8.0));
            exerciseRepository.save(new Exercise("ヨガ", 2.5));

            
        }
    }
}
