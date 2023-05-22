package com.nikron.library.dao;

import com.nikron.library.model.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRowMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setId(rs.getInt("book_id"));
        book.setName(rs.getString("book_name"));
        book.setAuthor(rs.getString("book_author"));
        book.setYear(rs.getInt("book_date"));
        book.setUserTakeBook(rs.getObject("user_id", Integer.class));
        return book;
    }
}
