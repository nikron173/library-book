package com.nikron.springboot.librarybook.mapper;

import com.nikron.springboot.librarybook.dto.StudentBookDTO;
import com.nikron.springboot.librarybook.dto.StudentCreateDTO;
import com.nikron.springboot.librarybook.dto.StudentInfoDTO;
import com.nikron.springboot.librarybook.entity.Student;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Component
public class StudentMapper {
    public StudentInfoDTO studentInfoToDto(Student student){
        return new StudentInfoDTO(
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getBirthDay(),
                Period.between(student.getBirthDay(), LocalDate.now()).getYears()
        );
    }

    public StudentBookDTO studentBookDTO(Student student){
        return new StudentBookDTO(
                student.getName(),
                student.getEmail(),
                student.getBooks()
        );
    }

    public Student dtoToStudent(StudentCreateDTO studentCreateDTO){
        return new Student(
                studentCreateDTO.getName(),
                studentCreateDTO.getEmail(),
                studentCreateDTO.getBirthDay()
        );
    }
}
