package com.nikron.springboot.librarybook.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;

@Configuration
@RequiredArgsConstructor
public class DataConfig implements CommandLineRunner{
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void run(String[] args) throws Exception {
        String insertGenre = "insert into genre (genre_name) values (?)";
        String insertAuthor = "insert into author (author_name) values (?)";
        String insertStudent = "insert into student (student_name,email,birth_day) values (?,?,?)";

        String insertBookStudent = "insert into book (author_id,genre_id,student_id,book_name,create_date) values " +
                "((select author_id from author where author_name=?)," +
                "(select genre_id from genre where genre_name=?), " +
                "(select student_id from student where email=?),?,?) ";

        String insertBook = "insert into book (author_id,genre_id,book_name,create_date) values " +
                "((select author_id from author where author_name=?)," +
                "(select genre_id from genre where genre_name=?),?,?) ";

        jdbcTemplate.update(insertGenre, "Роман");
        jdbcTemplate.update(insertGenre, "Приключение");
        jdbcTemplate.update(insertGenre, "Повесть");

        jdbcTemplate.update(insertAuthor, "Михаил Булгаков");
        jdbcTemplate.update(insertAuthor, "Николай Гоголь");
        jdbcTemplate.update(insertAuthor, "Лев Толстой");
        jdbcTemplate.update(insertAuthor, "Антон Чехов");
        jdbcTemplate.update(insertAuthor, "Александр Пушкин");

        jdbcTemplate.update(insertStudent, "Nikita", "nikron17355@yandex.ru",
                LocalDate.of(1997, 8, 30));
        jdbcTemplate.update(insertStudent, "Vika", "vika@yandex.ru",
                LocalDate.of(1999, 3, 2));

        jdbcTemplate.update(insertBook, "Михаил Булгаков", "Роман", "Мастер и Маргарита",
                LocalDate.of(1927,1,3));
        jdbcTemplate.update(insertBook, "Михаил Булгаков", "Приключение", "Собачье сердце",
                LocalDate.of(1963, 6, 21));
        jdbcTemplate.update(insertBook, "Николай Гоголь", "Повесть", "Мёртвые души",
                LocalDate.of(1922, 8, 2));
        jdbcTemplate.update(insertBook, "Лев Толстой", "Роман", "Война и мир",
                LocalDate.of(1781, 2, 13));
        jdbcTemplate.update(insertBookStudent, "Антон Чехов", "Роман", "nikron17355@yandex.ru" ,"Палата номер 6",
                LocalDate.of(1872, 3, 15));
    }
}
