package com.nikron.springboot.librarybook.controller;

import com.nikron.springboot.librarybook.dto.StudentBookDTO;
import com.nikron.springboot.librarybook.dto.StudentCreateDTO;
import com.nikron.springboot.librarybook.dto.StudentInfoDTO;
import com.nikron.springboot.librarybook.entity.Student;
import com.nikron.springboot.librarybook.error.BaseErrorHandler;
import com.nikron.springboot.librarybook.mapper.StudentMapper;
import com.nikron.springboot.librarybook.service.StudentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/student")
@AllArgsConstructor
public class StudentController {
    @NonNull private final StudentService studentService;
    @NonNull private final StudentMapper studentMapper;

    @GetMapping
    public List<StudentInfoDTO> getStudents(){
        return studentService.getStudents().stream()
                .map(studentMapper::studentInfoToDto).toList();
    }

    @GetMapping(path = "{id}/books")
    public ResponseEntity<StudentBookDTO> getStudentBooks(
            @PathVariable(name = "id", required = false) UUID id)
            throws BaseErrorHandler {
        Student student = studentService.getStudent(id);
        StudentBookDTO studentBookDTO = studentMapper.studentBookDTO(student);
        return new ResponseEntity<>(studentBookDTO, HttpStatusCode.valueOf(200));
    }

    @PostMapping
    public ResponseEntity<String> registerNewStudent(@RequestBody @Valid StudentCreateDTO student) throws BaseErrorHandler {
        UUID id = studentService.registerNewStudent(studentMapper.dtoToStudent(student));
        return new ResponseEntity<>(String.format("Student id: %s created.", id),
                HttpStatusCode.valueOf(200));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") UUID id) throws BaseErrorHandler {
        studentService.deleteStudent(id);
        return new ResponseEntity<>(String.format("Student id: %s deleted.", id),
                HttpStatusCode.valueOf(200));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<String> updateStudent(@PathVariable("id") UUID id,
                              @RequestBody @Valid StudentCreateDTO studentCreateDTO) throws BaseErrorHandler {
        studentService.updateStudent(id, studentMapper.dtoToStudent(studentCreateDTO));
        return new ResponseEntity<>(String.format("Student id: %s updated.", id),
                HttpStatusCode.valueOf(201));
    }
}
