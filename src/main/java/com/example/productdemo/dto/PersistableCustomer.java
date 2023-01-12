package com.example.productdemo.dto;

import lombok.Data;

//This object is a model which comes from client side
@Data
public class PersistableCustomer {

    private int customerId;

    private String firstName;

    private String lastName;

    private String email;

}