package com.nikron.springboot.librarybook.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;


@Entity
@Table(name = "book")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"author", "genre", "student"})
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "author_id",
            nullable = false,
            columnDefinition = "UUID"
    )
    @JsonIgnore
    @NonNull private Author author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "genre_id",
            nullable = false,
            columnDefinition = "UUID"
    )
    @JsonIgnore
    @NonNull private Genre genre;

    @Column(
            name = "create_date",
            columnDefinition = "DATE"
    )
    @NonNull private LocalDate createDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "student_id",
            columnDefinition = "UUID"
    )
    @JsonIgnore
    private Student student;
}
