package com.rf.ecommerce.Controller.User;

import com.rf.ecommerce.Entity.User.User;
import com.rf.ecommerce.Service.User.UserService;
import com.rf.ecommerce.error.ApiError;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserService userService;
    // kaydolma
    @PostMapping("/createUser")
    @CrossOrigin
    public ResponseEntity<?> createUser(@Valid @RequestBody User user){
        if(userService.existsByEmail(user.getEmail())){
            ApiError apiError=new ApiError(400,"kullanici zaten kayıtlı","/api/createUser");
            Map<String,String> validationError=new HashMap<>();
            validationError.put("email","email adi daha önce seçilmiş");
            apiError.setValidationErrors(validationError);
           return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
        }
        else {
            user.setPassword(userService.passwordEncoder.encode(user.getPassword()));
            userService.save(user);
            return ResponseEntity.ok("Kullanici başari ile kayıt oldu");
        }
    }
    // giriş
    // şifremi unuttum
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError hataGonder(MethodArgumentNotValidException exception){
        ApiError apiError=new ApiError(400,"Hata","/api/user");
        Map<String,String> validationErross=new HashMap<>();
        for(FieldError fieldError: exception.getBindingResult().getFieldErrors()){
            validationErross.put(fieldError.getField(),fieldError.getDefaultMessage());
            apiError.setValidationErrors(validationErross);
        }
        return  apiError;
    }
}
