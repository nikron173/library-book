package com.nikron.springboot.librarybook.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PreRemove;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(
        name = "student"
)
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(
            strategy = GenerationType.UUID
    )
    @Column(
            name = "student_id",
            columnDefinition = "UUID DEFAULT gen_random_uuid()"
    )
    private UUID id;
    @NonNull
    @Size(min = 3, max = 20, message = "Student name range from 3 to 20 character")
    @Column(
            name = "student_name"
    )
    private String name;
    @Column(
            name = "email",
            unique = true
    )
    @NonNull private String email;
    @Column(
            name = "birth_day",
            columnDefinition = "DATE"
    )
    @NonNull private LocalDate birthDay;

    @OneToMany(
            mappedBy = "student"
    )
    private List<Book> books = new ArrayList<>();

    @PreRemove
    public void TableBookNullStudentId(){
        books.forEach(book -> book.setStudent(null));
    }
}
