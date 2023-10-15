package com.nikron.springboot.librarybook.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;


@Entity
@Table(name = "book")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(
            strategy = GenerationType.UUID
    )
    @Column(
            name = "book_id",
            columnDefinition = "UUID DEFAULT gen_random_uuid()"
    )
    private UUID id;
    @Column(
            name = "book_name",
            nullable = false
    )
    @NonNull
    @Size(min = 3, max = 20, message = "Book name range from 7 to 20 character")
    private String bookName;

    @ManyToOne
    @JoinColumn(
            name = "author_id",
            nullable = false
    )
    @NonNull private Author author;

    @ManyToOne
    @JoinColumn(
            name = "genre_id",
            nullable = false
    )
    @NonNull private Genre genre;

    @Column(
            name = "create_date",
            columnDefinition = "DATE"
    )
    @NonNull private LocalDate createDate;

    @ManyToOne
    @JoinColumn(
            name = "student_id",
            columnDefinition = "UUID"
    )
    private Student student;
}
