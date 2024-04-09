package com.seinfeld.search.mappers;

import com.seinfeld.search.dtos.responses.StudentResponse;
import com.seinfeld.search.models.Student;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;

@Mapper(componentModel = "spring")
public abstract class StudentMapper {

    public abstract StudentResponse studentToStudentResponse(Student student);

    public abstract List<StudentResponse> listStudentResponse(List<Student> students);

    public Page<StudentResponse> pageStudentResponse(Page<Student> students){
        return students.map(new Function<Student, StudentResponse>(){
            @Override
            public StudentResponse apply(Student student) {
                return studentToStudentResponse(student);
            }
        });
    }

}
