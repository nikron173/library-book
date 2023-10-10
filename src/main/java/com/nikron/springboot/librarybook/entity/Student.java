package com.nikron.springboot.librarybook.entity;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;

import com.nikron.springboot.librarybook.entity.Book;
import jakarta.persistence.*;

@Entity
@Table
public class Student {
    @Id
    @SequenceGenerator(name = "student_sequence",
                    sequenceName = "student_sequence",
                    allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "student_sequence")
    private Long id;
    private String name;
    private String email;
    private LocalDate birthDay;

    @Transient
    private Integer age;

    @OneToMany(mappedBy = "student")
    private Set<Book> books = new HashSet<>();

    public Student() {
    }

    public Student(Long id, String name, String email, LocalDate birthDay) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthDay = birthDay;
    }

    public Student(String name, String email, LocalDate birthDay) {
        this.name = name;
        this.email = email;
        this.birthDay = birthDay;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public Integer getAge() {
        return Period.between(this.birthDay, LocalDate.now()).getYears();
    }

    @PreRemove
    public void TableBookNullStudentId(){
        books.forEach(book -> book.setStudent(null));
    }
}
