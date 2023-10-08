package com.nikron.springboot.librarybook.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT s FROM Student s WHERE s.email = ?1")
    Optional<Student> getStudentByEmail(String email);

    @Query("SELECT s FROM Student s WHERE s.name = ?1")
    Optional<Student> getStudentByName(String studentName);

    @Query("SELECT s FROM Student s WHERE s.id = ?1")
    Optional<Student> getStudentById(Long id);
}
