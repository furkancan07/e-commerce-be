package com.rf.ecommerce.Repository.Product;

import com.rf.ecommerce.Entity.Product.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
