package com.seinfeld.search.dtos.responses;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.seinfeld.search.models.Address;
import com.seinfeld.search.models.Subject;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
public class StudentResponse {

    private Long id;

    private String name;

    private Address address;

    private List<SubjectResponse> subjects;
}
