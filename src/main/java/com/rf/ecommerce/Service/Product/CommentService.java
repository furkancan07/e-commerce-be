package com.rf.ecommerce.Service.Product;

import com.rf.ecommerce.Entity.Product.Comment;
import com.rf.ecommerce.Repository.Product.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private CommentRepository commentRepository;
    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getAllComments(){
        return commentRepository.findAll();
    }
    public void delete(Long id){
        commentRepository.deleteById(id);
    }
    public void save(Comment comment){
        commentRepository.save(comment);
    }
    public boolean existById(Long id){
        return commentRepository.existsById(id);
    }
    public Comment findById(Long id){
        return commentRepository.findById(id).orElseThrow();
    }
}
