package com.seinfeld.search.dtos.requests;

import lombok.Data;

import java.util.List;

@Data
public class Request {
    private List<SearchRequest> searchRequests;
    private GlobalOperator globalOperator;
    private PageRequestDto pageDto;
}
