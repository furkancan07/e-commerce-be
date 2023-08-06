package com.rf.ecommerce.Service.User;

import com.rf.ecommerce.Entity.User.User;
import com.rf.ecommerce.Repository.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    UserRepository userRepository;
    public PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public void save(User user){
        userRepository.save(user);
    }
    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }
    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }



}
