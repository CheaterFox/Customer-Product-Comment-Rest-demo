package com.example.productdemo.service;

import com.example.productdemo.dao.CommentRepository;
import com.example.productdemo.dto.PersistableComment;
import com.example.productdemo.dto.ReadableComment;
import com.example.productdemo.entity.Comment;
import com.example.productdemo.populator.CommentPopulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;

    @Autowired
    private CommentPopulator commentPopulator;


    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public ReadableComment findCommentById(int commentId) {

        Optional<Comment> optionalComment = commentRepository.findById(commentId);

        ReadableComment readableComment = null;

        if (optionalComment.isPresent()) {
            readableComment = commentPopulator.convertCommentToReadableComment(optionalComment.get());
        }
        else {
            throw new RuntimeException("Did not find comment id - " + commentId);
        }
        return readableComment;
    }

    @Override
    public void saveComment(PersistableComment persistableComment) {

        Comment comment = commentPopulator.convertPersistableCommentToComment(persistableComment);
        commentRepository.save(comment);
    }

    @Override
    public void deleteCommentById(int commentId) {

        Optional<Comment> comment = commentRepository.findById(commentId);

        if (comment.isPresent()) {
            commentRepository.deleteById(commentId);
        }
        else {
            throw new RuntimeException("Did not find comment id - " + commentId);
        }
    }

    @Override
    public List<ReadableComment> getAllComments() {

        List<Comment> commentList = commentRepository.findAll();
        List<ReadableComment> readableCommentList =
                commentPopulator.convertCommentListToReadableCommentList(commentList);
        return readableCommentList;
    }

    @Override
    public List<ReadableComment> getAllCommentsByCustomerId(int customerId) {
        List<Comment> commentListByCustomerId = commentRepository.findByCustomerId(customerId);
        List<ReadableComment> readableCommentListByCustomerId =
                commentPopulator.convertCommentListToReadableCommentList(commentListByCustomerId);
        return readableCommentListByCustomerId;
    }

    @Override
    public List<ReadableComment> getAllCommentsByCustomerIdBetweenDate(int customerId, LocalDate startDate, LocalDate endDate) {
        List<Comment> commentListByCustomerIdBetweenDate =
                commentRepository.findByCustomerIdAndCommentDateBetween(customerId, startDate, endDate);
        List<ReadableComment> readableCommentListByCustomerIdBetweenDate =
                commentPopulator.convertCommentListToReadableCommentList(commentListByCustomerIdBetweenDate);
        return readableCommentListByCustomerIdBetweenDate;
    }

    @Override
    public List<ReadableComment> getAllCommentsByProductId(int productId) {
        List<Comment> commentListByProductId = commentRepository.findByProductId(productId);
        List<ReadableComment> readableCommentListByProductId =
                commentPopulator.convertCommentListToReadableCommentList(commentListByProductId);
        return readableCommentListByProductId;
    }

    @Override
    public List<ReadableComment> getAllCommentsByProductIdBetweenDate(int productId, LocalDate startDate, LocalDate endDate) {
        List<Comment> commentListByProductIdBetweenDate =
                commentRepository.findByProductIdAndCommentDateBetween(productId, startDate, endDate);
        List<ReadableComment> readableCommentListByProductIdBetweenDate =
                commentPopulator.convertCommentListToReadableCommentList(commentListByProductIdBetweenDate);
        return readableCommentListByProductIdBetweenDate;
    }
}