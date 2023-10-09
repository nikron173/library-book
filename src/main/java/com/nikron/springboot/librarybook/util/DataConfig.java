package com.nikron.springboot.librarybook.util;

import com.nikron.springboot.librarybook.author.Author;
import com.nikron.springboot.librarybook.author.AuthorRepository;
import com.nikron.springboot.librarybook.book.Book;
import com.nikron.springboot.librarybook.book.BookRepository;
import com.nikron.springboot.librarybook.genre.Genre;
import com.nikron.springboot.librarybook.genre.GenreRepository;
import com.nikron.springboot.librarybook.student.Student;
import com.nikron.springboot.librarybook.student.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class DataConfig {

    @Bean
    CommandLineRunner commandLineRunner(BookRepository bookRepository,
                                        StudentRepository studentRepository,
                                        GenreRepository genreRepository,
                                        AuthorRepository authorRepository){
        return args -> {
            Genre genre1 = new Genre("Роман");
            Genre genre2 = new Genre("Приключение");
            Genre genre3 = new Genre("Повесть");
            genreRepository.saveAll(List.of(genre1, genre2, genre3));

            Author author1 = new Author("Михаил Булгаков");
            Author author2 = new Author("Николай Гоголь");
            Author author3 = new Author("Лев Толстой");
            Author author4 = new Author("Антон Чехов");
            Author author4 = new Author("Александр Пушкин");

            authorRepository.saveAll(List.of(author1, author2, author3, author4));

            Book book1 = new Book("Мастер и Маргарита",
                    author1,
                    genre1,
                    1929);
            Book book2 = new Book("Собачье сердце",
                    author1,
                    genre2,
                    1925);
            Book book3 = new Book("Мёртвые души",
                    author2,
                    genre3,
                    1842);
            Book book4 = new Book("Война и мир",
                    author3,
                    genre1,
                    1865);
            Book book5 = new Book("Палата номер 6",
                    author4,
                    genre1,
                    1892);

            bookRepository.saveAll(List.of(book1, book2, book3, book4, book5));

            Student student1 = new Student("Nikita", "nikron17355@yandex.ru",
                    LocalDate.of(1997, Month.AUGUST, 30));
            Student student2 = new Student("Vika", "vika@yandex.ru",
                    LocalDate.of(1999, Month.MARCH, 2));
            studentRepository.saveAll(List.of(student1, student2));
        };
    }
}
