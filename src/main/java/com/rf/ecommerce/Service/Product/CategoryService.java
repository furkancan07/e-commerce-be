package com.rf.ecommerce.Service.Product;

import com.rf.ecommerce.Dto.DtoConvert;
import com.rf.ecommerce.Dto.Product.CategoryDto;
import com.rf.ecommerce.Entity.Product.Category;
import com.rf.ecommerce.Entity.Product.Product;
import com.rf.ecommerce.Repository.Product.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    DtoConvert dtoConvert;
    @Autowired
    @Lazy
    ProductService productService;

    public void save(Category category){
        categoryRepository.save(category);
    }
    public void delete(Long id){
        categoryRepository.deleteById(id);
    }
    public boolean existsByName(String name){
       return categoryRepository.existsByName(name);
    }
    public Category findByName(String name){
        return categoryRepository.findByName(name);
    }
    public boolean createAtCategory(Category category){
        if(existsByName(category.getName())){
            return false;
        }
        categoryRepository.save(category);
        return true;
    }
    public boolean deleteToCategory(Long categoryId){
        if(!categoryRepository.existsById(categoryId)){
            return false;
        }

        for (Product product : productService.getAllProducts() ){
            if(product.getCategory().getId().equals(categoryId)){
                productService.delete(product.getId());
            }
        }
        delete(categoryId);
        return true;
    }
    public List<CategoryDto> getCategories(){
        return categoryRepository.findAll().stream().map(x->dtoConvert.categoryConvert(x)).collect(Collectors.toList());
    }

}
