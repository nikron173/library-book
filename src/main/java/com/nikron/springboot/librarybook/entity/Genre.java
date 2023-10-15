package com.nikron.springboot.librarybook.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "genre")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Genre {
    @Id
    @GeneratedValue(
            strategy = GenerationType.UUID
    )
    @Column(
            name = "genre_id",
            columnDefinition = "UUID DEFAULT gen_random_uuid()"
    )
    private UUID id;
    @Column(
            name = "genre_name",
            unique = true,
            nullable = false
    )
    @NonNull
    @Size(min = 3, max = 20, message = "Genre name range from 3 to 20 character")
    private String genreName;

    @OneToMany(
            mappedBy = "genre",
            cascade = CascadeType.REMOVE,
            orphanRemoval = true
    )
    private List<Book> books = new ArrayList<>();
}
