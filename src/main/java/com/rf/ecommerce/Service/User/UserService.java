package com.rf.ecommerce.Service.User;

import com.rf.ecommerce.Dto.DtoConvert;
import com.rf.ecommerce.Dto.User.UserDto;
import com.rf.ecommerce.Entity.User.User;
import com.rf.ecommerce.Repository.User.UserRepository;
import com.rf.ecommerce.error.ApiError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    UserRepository userRepository;
    private final DtoConvert dtoConvert;
    public PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
    @Autowired
    public UserService(UserRepository userRepository, DtoConvert dtoConvert) {
        this.userRepository = userRepository;
        this.dtoConvert = dtoConvert;
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
    public List<UserDto> getAllUser(){
        return userRepository.findAll().stream().map(x->DtoConvert.userConvert(x)).collect(Collectors.toList());
    }
    public ResponseEntity<?> getUser(String email){
        if(!existsByEmail(email)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiError(404,"Kullanici Bulunamadi","api/user"));
        }
        User user=findByEmail(email);
        return ResponseEntity.ok(dtoConvert.userConvert(user));
    }





}
