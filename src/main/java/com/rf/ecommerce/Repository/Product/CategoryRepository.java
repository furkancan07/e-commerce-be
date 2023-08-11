package com.rf.ecommerce.Repository.Product;

import com.rf.ecommerce.Entity.Product.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    boolean existsByName(String name);
    Category findByName(String name);
}
