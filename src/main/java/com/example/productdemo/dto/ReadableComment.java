package com.example.productdemo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

//This object is a model which has sent to client side
@Data
public class ReadableComment {

    private String comment;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate commentDate;

    private String productName;

    private String customerFirstName;

    private String customerLastName;

}