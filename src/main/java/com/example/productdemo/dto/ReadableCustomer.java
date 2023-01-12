package com.example.productdemo.dto;

import lombok.Data;

//This object is a model which has sent to client side
@Data
public class ReadableCustomer {

    private String firstName;

    private String lastName;

    private String email;

}