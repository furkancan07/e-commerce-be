package com.rf.ecommerce.Controller.Product;

import com.rf.ecommerce.Dto.Product.CategoryDto;
import com.rf.ecommerce.Entity.Product.Category;
import com.rf.ecommerce.Service.Product.CategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CategoryController {

   private final CategoryService categoryService;
    // kategori oluşturma
    @PostMapping("/createCategory")
    public ResponseEntity<?> createCategory(@Valid @RequestBody Category category){

        return categoryService.createAtCategory(category);
    }
    // kategori ve bu kategorideki tüm ürünleri siler kullanırken dikkatli olun
    @DeleteMapping("/deleteCategory/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long categoryId){
        return categoryService.deleteToCategory(categoryId);
    }

    // kategorileri getirme
    @GetMapping("getCategories")
    public List<CategoryDto> getCategories(){
        return categoryService.getCategories();
    }
}
