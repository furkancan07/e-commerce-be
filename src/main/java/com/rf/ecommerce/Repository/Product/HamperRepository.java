package com.rf.ecommerce.Repository.Product;

import com.rf.ecommerce.Entity.Product.Hamper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HamperRepository extends JpaRepository<Hamper,Long> {
}
