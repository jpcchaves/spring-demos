package com.springdemos.springdemos.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "pets")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String color;
    private Integer yearsAge;
    private Integer monthsAge;
    private char gender;
    private String healthCondition;

    public Pet() {
    }

    public Pet(Long id,
               String name,
               String color,
               Integer yearsAge,
               Integer monthsAge,
               char gender,
               String healthCondition) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.yearsAge = yearsAge;
        this.monthsAge = monthsAge;
        this.gender = gender;
        this.healthCondition = healthCondition;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getYearsAge() {
        return yearsAge;
    }

    public void setYearsAge(Integer yearsAge) {
        this.yearsAge = yearsAge;
    }

    public Integer getMonthsAge() {
        return monthsAge;
    }

    public void setMonthsAge(Integer monthsAge) {
        this.monthsAge = monthsAge;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getHealthCondition() {
        return healthCondition;
    }

    public void setHealthCondition(String healthCondition) {
        this.healthCondition = healthCondition;
    }
}
