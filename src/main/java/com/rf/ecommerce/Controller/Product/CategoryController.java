package com.rf.ecommerce.Controller.Product;

import com.rf.ecommerce.Dto.Product.CategoryDto;
import com.rf.ecommerce.Entity.Product.Category;
import com.rf.ecommerce.Service.Product.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    // kategori oluşturma
    @PostMapping("/createCategory")
    public ResponseEntity<?> createCategory(@Valid @RequestBody Category category){
        if(categoryService.createAtCategory(category)){
            return ResponseEntity.ok("Kategori eklendi");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bu isimde zaten bir kategori mevcut");
    }
    // kategori ve bu kategorideki tüm ürünleri siler kullanırken dikkatli olun
    @DeleteMapping("/deleteCategory/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long categoryId){
        if(categoryService.deleteToCategory(categoryId)){
            return ResponseEntity.ok("Kategori ve altindaki tüm ürünler silindi");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Kategori Bulunamadı");
    }

    // kategorileri getirme
    @GetMapping("getCategories")
    public List<CategoryDto> getCategories(){
        return categoryService.getCategories();
    }
}
