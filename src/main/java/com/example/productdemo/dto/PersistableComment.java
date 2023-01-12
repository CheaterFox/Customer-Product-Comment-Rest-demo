package com.example.productdemo.dto;

import lombok.Data;

//This object is a model which comes from client side
@Data
public class PersistableComment {

    private int commentId;

    private String comment;

    private int customerId;

    private int productId;

}