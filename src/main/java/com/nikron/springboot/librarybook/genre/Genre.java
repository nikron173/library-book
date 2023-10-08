package com.nikron.springboot.librarybook.genre;

import com.nikron.springboot.librarybook.book.Book;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "genre")
public class Genre {

    @Id
    @SequenceGenerator(name = "genre_id_sequence",
            sequenceName = "genre_id_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "genre_id_sequence")
    @Column(name = "genre_id")
    private Long id;
    @Column(name = "genre_name",
    unique = true,
    nullable = false)
    private String genreName;

    @OneToMany(mappedBy = "genre", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Book> books;

    public Genre() {
    }

    public Genre(Long id, String genreName) {
        this.id = id;
        this.genreName = genreName;
    }

    public Genre(String genreName) {
        this.genreName = genreName;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Genre genre = (Genre) o;

        if (!Objects.equals(id, genre.id)) return false;
        return Objects.equals(genreName, genre.genreName);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (genreName != null ? genreName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", genre='" + genreName + '\'' +
                '}';
    }
}
