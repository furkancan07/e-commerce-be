package com.rf.ecommerce.Service.Product;

import com.rf.ecommerce.Entity.Product.Comment;
import com.rf.ecommerce.Repository.Product.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private CommentRepository commentRepository;
    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
}
