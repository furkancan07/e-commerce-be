package com.rf.ecommerce.Repository.Admin;

import com.rf.ecommerce.Entity.Admin.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Long> {
     Admin findByUsername(String username);
     boolean existsByUsername(String username);
}
