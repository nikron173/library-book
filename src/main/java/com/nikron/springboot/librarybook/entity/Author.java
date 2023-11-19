package com.nikron.springboot.librarybook.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "author")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Author {
    @Id
    @GeneratedValue(
            strategy = GenerationType.UUID
    )
    @Column(
            name = "author_id",
            columnDefinition = "UUID DEFAULT gen_random_uuid()"
    )
    private UUID id;

    @Column(
            name = "author_name",
            unique = true,
            nullable = false
    )
    @NonNull
    @Size(min = 7, max = 20, message = "Author name range from 7 to 20 character")
    private String authorName;

    @OneToMany(
            mappedBy = "author",
            cascade = CascadeType.REMOVE,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<Book> books = new ArrayList<>();
}
