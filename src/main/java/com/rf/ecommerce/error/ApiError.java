package com.rf.ecommerce.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError {
    private int status;
    private String mesage;
    private String path;
    private long timestap=new Date().getTime();
    private Map<String,String> validationErrors;

    public ApiError(int status, String mesage, String path) {
        this.status = status;
        this.mesage = mesage;
        this.path = path;
    }

}
