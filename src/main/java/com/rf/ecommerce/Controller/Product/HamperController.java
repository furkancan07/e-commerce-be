package com.rf.ecommerce.Controller.Product;

import com.rf.ecommerce.Entity.Product.Hamper;
import com.rf.ecommerce.Entity.Product.Product;
import com.rf.ecommerce.Entity.User.User;
import com.rf.ecommerce.Service.Product.HamperService;
import com.rf.ecommerce.Service.Product.ProductService;
import com.rf.ecommerce.Service.User.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api")
public class HamperController {
    @Autowired
    HamperService hamperService;
    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;
    // sepete ekleme
    @PostMapping("/addHamper/{email}/{productId}")
    @CrossOrigin
    public ResponseEntity<?> addHamper(@PathVariable String email,@PathVariable Long productId){
        if(userService.existsByEmail(email) && productService.existsById(productId)){
            Hamper hamper=new Hamper();

            User user=userService.findByEmail(email);
            Product product=productService.findById(productId);
            hamper.setUser(user);
            hamper.setProduct(product);
            hamperService.getAllHampers().add(hamper);
            hamperService.save(hamper);

           return  ResponseEntity.ok("Ürün Sepete Eklendi");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Kullanici veya Paylaşım bulunamadı");
    }

    // sepetten kaldirma
    @DeleteMapping("/deleteHampers/{hamperId}")
    @CrossOrigin
    public ResponseEntity<?> deleteHamper(@PathVariable Long hamperId){
        if(hamperService.existsById(hamperId)){
            Hamper hamper=hamperService.findById(hamperId);
            hamperService.delete(hamperId);
            hamperService.getAllHampers().remove(hamper);
            return ResponseEntity.ok("Sepetten ürün kaldırıldı");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ürün bulunamadı");
    }


    //sepettekilerin listesini getirme
    @GetMapping("/getHampers/{email}/{productId}")
    @CrossOrigin
    public List<Hamper> getHampers(@PathVariable String email,@PathVariable Long productId){
        List<Hamper> hampers=new ArrayList<>();

        for(Hamper hamper : hamperService.getAllHampers()){
            if(hamper.getUser().getEmail().equals(email)){
                hampers.add(hamper);
            }
        }
        return hampers;
    }

}
