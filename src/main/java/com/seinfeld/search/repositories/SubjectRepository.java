package com.seinfeld.search.repositories;

import com.seinfeld.search.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
