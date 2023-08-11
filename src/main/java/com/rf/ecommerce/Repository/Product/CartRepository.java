package com.rf.ecommerce.Repository.Product;

import com.rf.ecommerce.Entity.Product.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {

}
