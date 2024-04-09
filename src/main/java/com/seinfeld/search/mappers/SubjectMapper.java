package com.seinfeld.search.mappers;

import com.seinfeld.search.dtos.responses.SubjectResponse;
import com.seinfeld.search.models.Subject;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubjectMapper {

    SubjectResponse subjectToSubjectResponse(Subject subject);

    List<SubjectResponse> listSubjectResponse(List<Subject> subjects);
}
