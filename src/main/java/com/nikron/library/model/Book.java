package com.nikron.library.model;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class Book {

    private int id;

    @NotNull
    @Length(min = 3, max = 30, message = "Book title too long.")
    private String name;

    @NotNull
    @Pattern(regexp = "^([A-Z][a-z]{1,19}\\s){1,2}[A-Z][a-z]{1,19}$", message = "Author not valid.")
    private String author;
    @NotNull
    @Min(value = 1000, message = "Year not sub zero.")
    @Max(value = 2023, message = "Not valid year.")
    private int year;

    private Integer userTakeBook;

    public Integer getUserTakeBook() {
        return userTakeBook;
    }

    public Book() {
    }

    public Book(int id, String name, String author, int year) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public void setUserTakeBook(Integer userTakeBook) {
        this.userTakeBook = userTakeBook;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getInfoBook(){
        return String.format("%s, %s, %d", name, author, year);
    }
}
