package com.rf.ecommerce.Controller.User;

import com.rf.ecommerce.Dto.ChangePassword;
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

import java.util.Base64;
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
    @PostMapping("/userLogin")
    public ResponseEntity<?> userLogin(@RequestHeader(name = "Authorization",required = false)String authorization){
        User user=null;
        if(authorization==null){
            ApiError apiError=new ApiError(401,"Boş değer","/api/auth");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiError);
        }
        String[] basicSplit=authorization.split("Basic");
        String userpas=basicSplit[1];
        String userpass=userpas.split(" ")[1];
        String decode=new String(Base64.getDecoder().decode(userpass));
        System.out.println(decode);
        String email=decode.split(":")[0];
        String sifre=decode.split(":")[1];
        if(userService.existsByEmail(email)){
            user=userService.findByEmail(email);
            if(userService.passwordEncoder.matches(sifre,user.getPassword())){
                return  ResponseEntity.ok("Giriş Yapıldı");
            }else{
                ApiError apiError=new ApiError(401,"Yanliş Şifre","/api/user/auth");
                Map<String,String > validationErrors=new HashMap<>();
                validationErrors.put("password","Şifreniz Yanlış Tekrar Deneyin");
                apiError.setValidationErrors(validationErrors);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiError);
            }
        }
        else{
            ApiError apiError = new ApiError(401, "Hata", "/api/user/auth");
            Map<String,String > validationErrors=new HashMap<>();
            validationErrors.put("email","Sistemde Kayitli değilsiniz Kaydolun");
            apiError.setValidationErrors(validationErrors);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiError);
        }
    }

    // şifremi unuttum
    @PostMapping("/forgot")
    public ResponseEntity<?> forgotPassword(@Valid @RequestBody ChangePassword user){
        User updateUser;
        if(userService.existsByEmail(user.getEmail())){
            updateUser=userService.findByEmail(user.getEmail());
            updateUser.setPassword(user.getPassword());
            updateUser.setPassword(userService.passwordEncoder.encode(updateUser.getPassword()));
            userService.save(updateUser);
            return ResponseEntity.ok("Şifre değiştirildi");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Kullanici Bulunamadi");
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError sendError(MethodArgumentNotValidException exception){
        ApiError apiError=new ApiError(400,"Hata","/api/user");
        Map<String,String> validationErross=new HashMap<>();
        for(FieldError fieldError: exception.getBindingResult().getFieldErrors()){
            validationErross.put(fieldError.getField(),fieldError.getDefaultMessage());
            apiError.setValidationErrors(validationErross);
        }
        return  apiError;
    }
}
