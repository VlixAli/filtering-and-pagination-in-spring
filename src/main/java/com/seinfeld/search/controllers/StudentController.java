package com.seinfeld.search.controllers;

import com.seinfeld.search.dtos.requests.PageRequestDto;
import com.seinfeld.search.dtos.responses.StudentResponse;
import com.seinfeld.search.mappers.StudentMapper;
import com.seinfeld.search.models.Student;
import com.seinfeld.search.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentMapper studentMapper;

    @PostMapping
    public Page<StudentResponse> getAllStudents(@RequestBody PageRequestDto dto) {
        Pageable pageable = new PageRequestDto().getPageable(dto);
        Page<StudentResponse> studentPage =
                studentMapper.pageStudentResponse(studentRepository.findAll(pageable));
        return studentPage;
    }

    @PostMapping("/queryMethod/{city}")
    public Page<StudentResponse> getAllStudentsQueryMethod(@RequestBody PageRequestDto dto,
                                                           @PathVariable String city) {

        Pageable pageable = new PageRequestDto().getPageable(dto);
        Page<Student> studentPage = studentRepository.findByAddressCity(city, pageable);
        return studentMapper.pageStudentResponse(studentPage);
    }

    @PostMapping("/list")
    public Page<StudentResponse> getAllStudentsList(@RequestBody PageRequestDto dto) {

        List<Student> studentList = studentRepository.findAll();
        PagedListHolder<Student> pagedListHolder = new PagedListHolder<>(studentList);
        pagedListHolder.setPage(dto.getPageNo());
        pagedListHolder.setPageSize(dto.getPageSize());

        List<Student> pageSlice = pagedListHolder.getPageList();
        boolean ascending = dto.getSort().isAscending();

        PropertyComparator.sort(pageSlice, new MutableSortDefinition(dto.getSortByColumn(),
                true, ascending));

        Page<Student> students = new PageImpl<>(pageSlice, new PageRequestDto().getPageable(dto), studentList.size());
        return studentMapper.pageStudentResponse(students);
    }

}
