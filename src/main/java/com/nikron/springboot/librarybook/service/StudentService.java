package com.nikron.springboot.librarybook.service;

import com.nikron.springboot.librarybook.dto.StudentBookDTO;
import com.nikron.springboot.librarybook.entity.Student;
import com.nikron.springboot.librarybook.error.BaseErrorHandler;
import com.nikron.springboot.librarybook.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentService {

    @NonNull private final StudentRepository studentRepository;

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Student getStudent(UUID id) throws BaseErrorHandler {
        return studentRepository.findById(id).orElseThrow(() -> new BaseErrorHandler("Student with id " + id + " not found."));
    }

    public UUID registerNewStudent(Student student) throws BaseErrorHandler {
        Optional<Student> optionalStudent = studentRepository.getStudentByEmail(student.getEmail());
        if (optionalStudent.isPresent()){
            throw new BaseErrorHandler("Student with email: " + student.getEmail()
                    + " already taken.");
        }
        student.setId(studentRepository.save(student).getId());
        return student.getId();
    }

    public void deleteStudent(UUID id) throws BaseErrorHandler {
        if (!studentRepository.existsById(id)) {
            throw new BaseErrorHandler("Student with id " + id + " not found.");
        }
        studentRepository.deleteById(id);
    }

    @Transactional
    public void updateStudent(UUID id, Student student) throws BaseErrorHandler {
        Student studentOriginal = studentRepository.findById(id)
                .orElseThrow(() -> new BaseErrorHandler("Student with id " + id + " not found."));
        if (!Objects.equals(student.getName(), studentOriginal.getName())){
            studentOriginal.setName(student.getName());
        }
        if (!Objects.equals(student.getEmail(), studentOriginal.getEmail()) &&
            studentRepository.getStudentByEmail(student.getEmail()).isEmpty()){
            studentOriginal.setEmail(student.getEmail());
        }
        if (!Objects.equals(student.getBirthDay(), studentOriginal.getBirthDay())){
            studentOriginal.setBirthDay(student.getBirthDay());
        }
    }
}
