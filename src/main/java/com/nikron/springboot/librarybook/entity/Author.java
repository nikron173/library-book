package com.nikron.springboot.librarybook.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "author")
public class Author {
    @Id
    @SequenceGenerator(name = "author_id_sequence",
            sequenceName = "author_id_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "author_id_sequence")
    @Column(name = "author_id")
    private Long id;

    @Column(name = "author_name",
            unique = true,
            nullable = false)
    private String authorName;

    @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Book> books = new HashSet<>();

    public Author() {
    }

    public Author(String authorName) {
        this.authorName = authorName;
    }

    public Author(Long id, String authorName) {
        this.id = id;
        this.authorName = authorName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Author author = (Author) o;

        if (!Objects.equals(id, author.id)) return false;
        return Objects.equals(authorName, author.authorName);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (authorName != null ? authorName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", authorName='" + authorName + '\'' +
                ", books=" + books +
                '}';
    }
}
