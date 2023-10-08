package com.nikron.springboot.librarybook.book;

import com.nikron.springboot.librarybook.author.Author;
import com.nikron.springboot.librarybook.genre.Genre;
import com.nikron.springboot.librarybook.student.Student;
import jakarta.persistence.*;


@Entity
@Table
public class Book {
    @Id
    @SequenceGenerator(name = "book_id_sequence",
            sequenceName = "book_id_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "book_id_sequence")
    private Long id;
    @Column(name = "book_name",
            nullable = false)
    private String bookName;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @ManyToOne
    @JoinColumn(name = "genre_id", nullable = false)
    private Genre genre;

    @Column(name = "create_date",
            columnDefinition = "INT")
    private Integer createDate;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public Book(String bookName, Author author, Genre genre, Integer createDate) {
        this.bookName = bookName;
        this.author = author;
        this.genre = genre;
        this.createDate = createDate;
    }

    public Book() {
    }

    public Long getId() {
        return id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Integer getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Integer createDate) {
        this.createDate = createDate;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", author=" + author.getAuthorName() +
                ", genre=" + genre.getGenreName() +
                ", createDate=" + createDate +
                '}';
    }
}
