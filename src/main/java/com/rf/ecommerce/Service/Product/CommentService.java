package com.rf.ecommerce.Service.Product;

import com.rf.ecommerce.Dto.DtoConvert;
import com.rf.ecommerce.Dto.Product.CommentDto;
import com.rf.ecommerce.Entity.Product.Comment;
import com.rf.ecommerce.Entity.Product.Product;
import com.rf.ecommerce.Entity.User.User;
import com.rf.ecommerce.Repository.Product.CommentRepository;
import com.rf.ecommerce.Service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final DtoConvert dtoConvert;
    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;
    @Autowired
    public CommentService(CommentRepository commentRepository, DtoConvert dtoConvert) {
        this.commentRepository = commentRepository;
        this.dtoConvert = dtoConvert;
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
    public boolean addToComment(String email,Long productId,Comment comment){
        if(!userService.existsByEmail(email)&&!productService.existsById(productId)){
            return false;
        }
        User user=userService.findByEmail(email);
        Product product=productService.findById(productId);
        comment.setUser(user);
        comment.setProduct(product);
        save(comment);
        return true;
    }
    public List<CommentDto> getToComments(Long productId){
        List<Comment> commentList=new ArrayList<>();
        for(Comment comment: getAllComments()){
            if(comment.getProduct().getId().equals(productId)){
                commentList.add(comment);
            }
        }
        return commentList.stream().map(x->dtoConvert.commentConvert(x)).collect(Collectors.toList());
    }
    public boolean deleteToComment(Long commentId){
        if(!existById(commentId)){
            return false;
        }
        delete(commentId);
        return true;
    }

}
