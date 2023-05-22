package com.nikron.library.dao;

import com.nikron.library.model.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class PersonRowMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person person = new Person();
        String[] str = rs.getString("username").split(" ");
        person.setId(rs.getInt("user_id"));
        person.setLastName(str[0]);
        person.setFirstName(str[1]);
        person.setMidName(str[2]);
        person.setBirthDay(rs.getObject("birth_day", LocalDate.class));
        return person;
    }
}
