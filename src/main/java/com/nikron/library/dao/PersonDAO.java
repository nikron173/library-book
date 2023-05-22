package com.nikron.library.dao;

import com.nikron.library.model.Book;
import com.nikron.library.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index(){
        return jdbcTemplate.query("SELECT * FROM person ORDER BY user_id", new PersonRowMapper());
    }

    public Person show(int id){
        return jdbcTemplate.query("SELECT * FROM person WHERE user_id=?", new Object[]{id}, new PersonRowMapper())
                .stream().findAny().orElse(null);
    }

    public Optional<Person> show(String fullUsername){
        return jdbcTemplate.query("SELECT * FROM person WHERE username=?", new Object[]{fullUsername}, new PersonRowMapper())
                .stream().findAny();
    }

    public List<Book> showBookPerson(int id){
        return jdbcTemplate.query("SELECT * FROM book WHERE user_id=?", new Object[]{id}, new BookRowMapper());
    }

    public void deletePerson(int id){
        jdbcTemplate.update("DELETE FROM person WHERE user_id=?", id);
    }

    public void createPerson(Person person){
        jdbcTemplate.update("INSERT INTO person(username, birth_day) VALUES (?, ?)",
                person.getFullName(), person.getBirthDay());
    }

    public void updatePerson(Person person) {
        jdbcTemplate.update("UPDATE person SET username=?, birth_day=? WHERE user_id=?",
                person.getFullName(), person.getBirthDay(), person.getId());
    }
}
