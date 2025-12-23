package com.greencity.api.models;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorResponse {
    public Date timestamp;
    public Integer status;
    public String error;
    public String path;
}
