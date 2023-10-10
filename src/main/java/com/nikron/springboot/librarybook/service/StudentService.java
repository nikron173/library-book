package com.nikron.springboot.librarybook.service;

import com.nikron.springboot.librarybook.entity.Student;
import com.nikron.springboot.librarybook.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void registerNewStudent(Student student) {
        Optional<Student> optionalStudent = studentRepository.getStudentByEmail(student.getEmail());
        if (optionalStudent.isPresent()){
            throw new IllegalStateException("Student with email: " + student.getEmail()
                    + " already taken.");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new IllegalStateException("Student with id " + id + " not found.");
        }
        studentRepository.deleteById(id);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.getStudentById(studentId)
                .orElseThrow(() -> new IllegalStateException("Student with id " + studentId + " not found."));

        if (name != null && name.length() > 2
                && !Objects.equals(student.getName(), name)){
            student.setName(name);
        }
        if (email != null && email.matches(".*@.*\\..*")
            && !Objects.equals(student.getEmail(), email)){
            student.setEmail(email);
        }
    }
}
