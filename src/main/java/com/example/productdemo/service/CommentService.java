package com.example.productdemo.service;

import com.example.productdemo.dto.PersistableComment;
import com.example.productdemo.dto.ReadableComment;

import java.time.LocalDate;
import java.util.List;

public interface CommentService {

    ReadableComment findCommentById(int commentId);

    void saveComment(PersistableComment persistableComment);

    void deleteCommentById(int commentId);
    List<ReadableComment> getAllComments();

    List<ReadableComment> getAllCommentsByCustomerId(int customerId);

    List<ReadableComment> getAllCommentsByCustomerIdBetweenDate(int customerId, LocalDate startDate, LocalDate endDate);

    List<ReadableComment> getAllCommentsByProductId(int productId);

    List<ReadableComment> getAllCommentsByProductIdBetweenDate(int productId, LocalDate startDate, LocalDate endDate);
}