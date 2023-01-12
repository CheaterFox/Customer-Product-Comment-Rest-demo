package com.example.productdemo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

//This object is a model which comes from client side
@Data
public class PersistableProduct {

    private int productId;

    private String productName;

    private Long price;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

}