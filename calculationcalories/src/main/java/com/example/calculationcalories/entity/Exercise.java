package com.example.calculationcalories.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "exercise")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double mets;

    public Exercise() {
    }

    public Exercise(String name, double mets) {
        this.name = name;
        this.mets = mets;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMets() {
        return mets;
    }

    public void setMets(double mets) {
        this.mets = mets;
    }
}
