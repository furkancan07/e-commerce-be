package com.rf.ecommerce.Controller.Admin;

import com.rf.ecommerce.Entity.Admin.Admin;
import com.rf.ecommerce.Service.Admin.AdminService;
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
public class AdminController {
    @Autowired
    AdminService adminService;
@PostMapping("/createAdmin")
@CrossOrigin
    public ResponseEntity<?> createAdmin(@Valid @RequestBody Admin admin){
        ApiError apiError;
      if(adminService.existsByUsername(admin.getUsername())){
          apiError=new ApiError(400,"bu kullanici adi daha once secilmiş","api/admin");
          Map<String,String> validationErrors=new HashMap<>();
          validationErrors.put("username","bu kullanici adi daha once secilmiş");
          apiError.setValidationErrors(validationErrors);
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);

      }
      else {
          admin.setPassword(adminService.passwordEncoder.encode(admin.getPassword()));
          adminService.save(admin);
          return ResponseEntity.ok("Başarı ile kayıt olundu");
      }
    }
    @PostMapping("/adminLogin")
    @CrossOrigin
    public ResponseEntity<?> login(@RequestHeader(name = "Authorization",required = false) String  authorization){
        Admin admin=null;
    if(authorization==null){
        ApiError apiError=new ApiError(401,"Boş değer","/api/auth");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiError);
    }
    String[] basicSplit=authorization.split("Basic");
    String userpas=basicSplit[1];
    String userpass=userpas.split(" ")[1];
    String decode=new String(Base64.getDecoder().decode(userpass));
    System.out.println(decode);
    String username=decode.split(":")[0];
    String sifre=decode.split(":")[1];
    if(adminService.existsByUsername(username)){
     admin=adminService.findByUsername(username);
     if(adminService.passwordEncoder.matches(sifre,admin.getPassword())){
         return  ResponseEntity.ok("Giriş Yapıldı");
     }else{
         ApiError apiError=new ApiError(401,"Yanliş Şifre","/api/auth");
         Map<String,String > validationErrors=new HashMap<>();
         validationErrors.put("password","Şifreniz Yanlış Tekrar Deneyin");
         apiError.setValidationErrors(validationErrors);
         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiError);
     }
    }
    else{
        ApiError apiError = new ApiError(401, "Hata", "/api/auth");
        Map<String,String > validationErrors=new HashMap<>();
        validationErrors.put("username","Sistemde Kayitli değilsiniz Kaydolun");
        apiError.setValidationErrors(validationErrors);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiError);
    }

    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError sendError(MethodArgumentNotValidException exception){
        ApiError apiError=new ApiError(400,"Hata","/api/admin");
        Map<String,String> validationErross=new HashMap<>();
        for(FieldError fieldError: exception.getBindingResult().getFieldErrors()){
            validationErross.put(fieldError.getField(),fieldError.getDefaultMessage());
            apiError.setValidationErrors(validationErross);
        }
        return  apiError;
    }
}
