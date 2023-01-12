package com.example.productdemo.populator;

import com.example.productdemo.dto.ReadableComment;
import com.example.productdemo.entity.Comment;
import com.example.productdemo.entity.Customer;
import com.example.productdemo.entity.Product;
import com.example.productdemo.dto.PersistableComment;
import com.example.productdemo.service.CustomerService;
import com.example.productdemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

//Converting persistable model to domain model or domain model to readable model
@Component
public class CommentPopulator {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;


    public Comment convertPersistableCommentToComment(PersistableComment persistableComment) {

        Comment comment = new Comment();
        comment.setId(persistableComment.getCommentId());
        Product product = productService.findProductByIdForPopulator(persistableComment.getProductId());
        Customer customer = customerService.findCustomerByIdForPopulator(persistableComment.getCustomerId());

        comment.setComment(persistableComment.getComment());
        comment.setCommentDate(LocalDate.now());
        comment.setCustomer(customer);
        comment.setProduct(product);

        return comment;
    }

    public ReadableComment convertCommentToReadableComment(Comment comment) {

        ReadableComment readableComment = new ReadableComment();
        readableComment.setComment(comment.getComment());
        readableComment.setCommentDate(comment.getCommentDate());
        readableComment.setProductName(comment.getProduct().getProductName());
        readableComment.setCustomerFirstName(comment.getCustomer().getFirstName());
        readableComment.setCustomerLastName(comment.getCustomer().getLastName());

        return readableComment;
    }

    public List<ReadableComment> convertCommentListToReadableCommentList(List<Comment> commentList) {

        return commentList.stream().map(this::convertCommentToReadableComment).toList();
    }
}