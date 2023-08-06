package com.rf.ecommerce.Service.Admin;

import com.rf.ecommerce.Entity.Admin.Admin;
import com.rf.ecommerce.Repository.Admin.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private AdminRepository adminRepository;
    public PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }
    public void save(Admin admin){
        adminRepository.save(admin);
    }
    public Admin findByUsername(String username){
        return  adminRepository.findByUsername(username);
    }
    public  boolean existsByUsername(String username){
        return  adminRepository.existsByUsername(username);
    }

}
