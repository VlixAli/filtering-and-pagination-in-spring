package com.seinfeld.search.controllers;

import com.seinfeld.search.dtos.requests.PageRequestDto;
import com.seinfeld.search.dtos.requests.Request;
import com.seinfeld.search.dtos.responses.StudentResponse;
import com.seinfeld.search.mappers.StudentMapper;
import com.seinfeld.search.models.Student;
import com.seinfeld.search.repositories.StudentRepository;
import com.seinfeld.search.services.FilterSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filter")
public class FilterController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private FilterSpecification<Student> filterSpecification;

    @GetMapping("/{name}")
    public StudentResponse getStudentByName(@PathVariable String name) {
        return studentMapper.studentToStudentResponse(studentRepository.findByName(name).get());
    }

    @GetMapping("/city/{city}")
    public List<StudentResponse> getStudentByCity(@PathVariable String city) {
        return studentMapper.listStudentResponse(studentRepository.findByAddressCity(city));
    }

    @GetMapping("/subject/{subject}")
    public List<StudentResponse> getStudentBySubject(@PathVariable String subject) {

        return studentMapper.listStudentResponse(studentRepository.findBySubjectsName(subject));
    }

    @PostMapping("/specification")
    public List<StudentResponse> getStudents(@RequestBody Request request) {
        Specification<Student> studentSpecification = filterSpecification
                .getSearchSpecification(request.getSearchRequests(), request.getGlobalOperator());
        List<StudentResponse> studentResponses = studentMapper
                .listStudentResponse(studentRepository.findAll(studentSpecification));
        return studentResponses;
    }

    @PostMapping("/specification/pagination")
    public Page<StudentResponse> getStudentsPage(@RequestBody Request request) {
        Specification<Student> studentSpecification = filterSpecification
                .getSearchSpecification(request.getSearchRequests(), request.getGlobalOperator());

        Pageable pageable = new PageRequestDto().getPageable(request.getPageDto());

        Page<StudentResponse> studentResponses = studentMapper
                .pageStudentResponse(studentRepository.findAll(studentSpecification, pageable));
        return studentResponses;
    }
}
