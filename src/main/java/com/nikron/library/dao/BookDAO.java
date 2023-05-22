package com.nikron.library.dao;

import com.nikron.library.model.Book;
import com.nikron.library.model.Person;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index(){
        return jdbcTemplate.query("SELECT * FROM book ORDER BY book_id", new BookRowMapper());
    }

    public Book show(int id){
        return jdbcTemplate.query("SELECT * FROM book WHERE book_id=?", new Object[]{id}, new BookRowMapper())
                .stream().findAny().orElse(null);
    }

    public Integer showBookEmployment(int id){
        return jdbcTemplate.queryForObject("SELECT user_id FROM book WHERE book_id=?", new Object[]{id}, Integer.class);
    }

    public Optional<Book> show(Book book){
        return jdbcTemplate.query("SELECT * FROM book WHERE book_name=? and book_author=?", new Object[]{book.getName(), book.getAuthor()}, new BookRowMapper())
                .stream().findAny();
    }

    public void deleteBook(int id){
        jdbcTemplate.update("DELETE FROM book WHERE book_id=?", id);
    }

    public void createBook(Book book){
        jdbcTemplate.update("INSERT INTO book(book_name, book_author, book_date) VALUES (?, ?, ?)",
                book.getName(), book.getAuthor(), book.getYear());
    }

    public void updateBook(Book book) {
        jdbcTemplate.update("UPDATE book SET book_name=?, book_author=?, book_date=? WHERE book_id=?",
                book.getName(), book.getAuthor(), book.getYear(), book.getId());
    }

    public void updateBook(Integer book_id, Integer user_id) {
        jdbcTemplate.update("UPDATE book SET user_id=? WHERE book_id=?",
                user_id, book_id);
    }
}
