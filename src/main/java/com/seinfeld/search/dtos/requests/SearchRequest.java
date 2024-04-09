package com.seinfeld.search.dtos.requests;

import lombok.Data;

@Data
public class SearchRequest {

    String column;
    String value;
    Operation operation;
    String joinTable;


    public enum Operation{
        EQUAL, LIKE, IN, GREATER_THAN, LESS_THAN, BETWEEN, JOIN
    }
}
