package com.example.productdemo.dao;

import com.example.productdemo.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByCustomerId(Integer customerId);

    List<Comment> findByProductId(Integer productId);

    List<Comment> findByCustomerIdAndCommentDateBetween(Integer customerId, LocalDate startDate, LocalDate endDate);

    List<Comment> findByProductIdAndCommentDateBetween(Integer productId, LocalDate startDate, LocalDate endDate);
}