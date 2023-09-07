package com.rf.ecommerce.Controller.Product;

import com.rf.ecommerce.Dto.Product.CommentDto;
import com.rf.ecommerce.Entity.Product.Comment;
import com.rf.ecommerce.Entity.Product.Product;
import com.rf.ecommerce.Entity.User.User;
import com.rf.ecommerce.Service.Product.CommentService;
import com.rf.ecommerce.Service.Product.ProductService;
import com.rf.ecommerce.Service.User.UserService;
import com.rf.ecommerce.error.ApiError;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // yorum ekleme
    @PostMapping("/addComment/{email}/{productId}")
    @CrossOrigin
    public ResponseEntity<?> addComment(@PathVariable String email, @PathVariable Long productId, @Valid @RequestBody Comment comment){
        return commentService.addToComment(email,productId,comment);
    }

    // bir paylaşıma ait yorumları getirme
    @GetMapping("getComments/{productId}")
    @CrossOrigin
    public List<CommentDto> getComments(@PathVariable Long productId){
        return commentService.getToComments(productId);
    }
    // yorum silme
    @DeleteMapping("/deleteComment/{commentId}")
    @CrossOrigin
    public ResponseEntity<?> deleteComment(@PathVariable Long commentId){
        return commentService.deleteToComment(commentId);
    }


}
