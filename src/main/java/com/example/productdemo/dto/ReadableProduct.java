package com.example.productdemo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

//This object is a model which has sent to client side
@Data
public class ReadableProduct {

    private String productName;

    private Long price;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
}