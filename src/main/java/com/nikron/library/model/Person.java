package com.nikron.library.model;

//import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.Date;

public class Person {
    private int id;

    @NotNull
    @Pattern(regexp = "^[A-Z][a-z]{1,19}$", message = "First name not valid.")
    private String firstName;

    @NotNull
    @Pattern(regexp = "^[A-Z][a-z]{1,19}$", message = "Last name not valid.")
    private String lastName;
    @NotNull
    @Pattern(regexp = "^[A-Z][a-z]{1,19}$", message = "Mid name not valid.")
    private String midName;

    @NotNull(message = "Date field value is empty.")
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate birthDay;

    public Person() {
    }

    public Person(int id, String firstName, String lastName, String midName, LocalDate birthDay) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.midName = midName;
        this.birthDay = birthDay;
    }

    public String getFullName(){
        return String.format("%s %s %s", lastName, firstName, midName);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMidName() {
        return midName;
    }

    public void setMidName(String midName) {
        this.midName = midName;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public int getYearBirthDay(){
        return birthDay.getYear();
    }
}
