package com.kmiura.champions.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiError {
    
    private int status;
    private String message;

}
