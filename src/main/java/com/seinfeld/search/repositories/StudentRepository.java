package com.seinfeld.search.repositories;

import com.seinfeld.search.models.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long>,
        JpaSpecificationExecutor<Student> {

    Optional<Student> findByName(String name);

    List<Student> findByAddressCity(String city);
    Page<Student> findByAddressCity(String city, Pageable pageable);

    List<Student> findBySubjectsName(String subjectName);
}
