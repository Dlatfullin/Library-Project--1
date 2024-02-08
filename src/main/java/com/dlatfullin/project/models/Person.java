package com.dlatfullin.project.models;

import jakarta.validation.constraints.*;

public class Person {

    private int person_id;
    @NotEmpty(message = "Name can't be empty")
    @Size(min = 2, max = 200, message = "Name should be between 2 and 200 characters")
    private String fullName;

    @Min(value = 0, message = "Year should be greater than 0")
    private int yearOfBirth;


    public Person(int person_id, String fullName, int yearOfBirth) {
        this.person_id = person_id;
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
    }


    public Person() {
    }


    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
}
