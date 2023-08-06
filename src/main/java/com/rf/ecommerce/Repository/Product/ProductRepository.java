package com.rf.ecommerce.Repository.Product;

import com.rf.ecommerce.Entity.Product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {


}
