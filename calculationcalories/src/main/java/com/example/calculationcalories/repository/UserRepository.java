package com.example.calculationcalories.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.calculationcalories.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
