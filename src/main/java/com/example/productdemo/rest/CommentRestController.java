package com.example.productdemo.rest;

import com.example.productdemo.dto.ReadableComment;
import com.example.productdemo.dto.PersistableComment;
import com.example.productdemo.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentRestController {

    private final CommentService commentService;

    public CommentRestController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<ReadableComment> getAllComments(){

        return commentService.getAllComments();
    }
    @GetMapping("/{commentId}")
    public ReadableComment findCommentById(@PathVariable int commentId) {
        return commentService.findCommentById(commentId);
    }

    @GetMapping("/customers/{customerId}")
    public List<ReadableComment> getAllCommentsByCustomerId(@PathVariable int customerId) {
        return commentService.getAllCommentsByCustomerId(customerId);
    }

    @GetMapping("/customersByDate/{customerId}") // This method returning comments by specific customer between comment date params
    public List<ReadableComment> getAllCommentsByCustomerIdBetweenDate(
            @PathVariable int customerId,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        return commentService.getAllCommentsByCustomerIdBetweenDate(customerId, startDate, endDate);
    }

    @GetMapping("/products/{productId}")
    public List<ReadableComment> getAllCommentsByProductId(@PathVariable int productId) {
        return commentService.getAllCommentsByProductId(productId);
    }

    @GetMapping("/productsByDate/{productId}") // This method returning comments by specific product between comment date params
    public List<ReadableComment> getAllCommentsByProductIdBetweenDate(
            @PathVariable int productId,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        return commentService.getAllCommentsByProductIdBetweenDate(productId, startDate, endDate);
    }

    @PostMapping
    public void saveComment(@RequestBody PersistableComment persistableComment) {

        commentService.saveComment(persistableComment);
    }

    @PutMapping
    public void updateComment(@RequestBody PersistableComment persistableComment) {

        commentService.saveComment(persistableComment);
    }

    @DeleteMapping("{commentId}")
    public String deleteCommentById(@PathVariable int commentId) {

        commentService.deleteCommentById(commentId);

        return "Deleted comment id - " + commentId;
    }
}