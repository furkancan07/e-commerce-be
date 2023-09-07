package com.rf.ecommerce.Service.Product;


import com.rf.ecommerce.Dto.DtoConvert;
import com.rf.ecommerce.Dto.Product.LikeDto;
import com.rf.ecommerce.Dto.Product.ProductDto;
import com.rf.ecommerce.Entity.Admin.Admin;
import com.rf.ecommerce.Entity.Order.Order;
import com.rf.ecommerce.Entity.Product.*;
import com.rf.ecommerce.Repository.Product.ProductRepository;
import com.rf.ecommerce.Service.Admin.AdminService;
import com.rf.ecommerce.Service.Order.OrderService;
import com.rf.ecommerce.error.ApiError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class ProductService {
    private final ProductRepository productRepository;
    private final DtoConvert dtoConvert;
    @Autowired
    private AdminService adminService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    @Lazy
    private OrderService orderService;
    @Autowired
    @Lazy
    private LikeService likeService;
    @Autowired
    @Lazy
    CommentService commentService;
    @Autowired
    @Lazy
    CartService cartService;



    @Autowired
    public ProductService(ProductRepository productRepository, DtoConvert dtoConvert) {
        this.productRepository = productRepository;
        this.dtoConvert = dtoConvert;
    }
    public void save(Product product){
        productRepository.save(product);
    }
    public void delete(Long id){
      productRepository.deleteById(id);
    }
    public boolean existsById(Long id){
        return productRepository.existsById(id);
    }
    public Product findById(Long id){
        return productRepository.findById(id).orElseThrow();
    }

    public List<Product> getAllProducts(){
        return  productRepository.findAll();
    }
    public ResponseEntity<?> createToProduct(String username, Product product){
        if(!adminService.existsByUsername(username) || !categoryService.existsByName(product.getCategoryName())){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(sendError("Kategori Bulunamadi"));
        }

        Admin admin=adminService.findByUsername(username);
        product.setAdmin(admin);
        Category category=categoryService.findByName(product.getCategoryName());
        product.setCategory(category);
        save(product);
        Like like=new Like();
        like.setProduct(product);
        likeService.save(like);
        return ResponseEntity.ok().body(200);
    }
    public ResponseEntity<?> deleteToProduct(Long id){
        if(!existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(sendError("Ürün Bulunamadi"));
        }
        for (Order order : orderService.getAllOrders()){
            if(order.getProduct().getId().equals(id)){
                orderService.delete(order.getId());
            }
        }
        for(Like like : likeService.getAllLikes()){
            if(like.getProduct().getId().equals(id)){
                likeService.delete(like.getId());
            }
        }
        for(Comment comment : commentService.getAllComments()){
            if(comment.getProduct().getId().equals(id)){
                commentService.delete(comment.getId());
            }
        }
        for(Cart cart : cartService.getAllCarts()){
            if(cart.getProduct().getId().equals(id)){
                cartService.deleteToCart(cart.getId());
            }
        }

        delete(id);

        return ResponseEntity.ok().body("Ürün Silindi");
    }
    public ResponseEntity<?> updateToProduct(Long id,ProductDto product){
        if(!existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(sendError("Ürün Bulunamadi"));
        }
        Product product1=findById(id);
        product1.setTitle(product.getTitle());
        product1.setDescription(product.getDescription());
        product1.setPrice(product.getPrice());
        save(product1);
        return  ResponseEntity.ok().body(200);
    }
    public void deleteAll(){
        productRepository.deleteAll();
    }
    public List<ProductDto> getToProducts(String username){
        List<Product> productList=new ArrayList<>();
        for(Product product: getAllProducts()){
            if(product.getAdmin().getUsername().equals(username)){
                productList.add(product);
            }
        }
        return  productList.stream().map(x->dtoConvert.productConvert(x)).collect(Collectors.toList());
    }
    public List<ProductDto> getToCategoryProducts(String category){
        List<Product> productList=new ArrayList<>();
        for(Product product : getAllProducts()){
            if(product.getCategory().getName().equals(category)){
                productList.add(product);
            }
        }
        return productList.stream().map(x->dtoConvert.productConvert(x)).collect(Collectors.toList());
    }
    public ProductDto getProduct(Long id){
        Product product=findById(id);
        ProductDto productDto= dtoConvert.productConvert(product);
        return productDto;
    }
    private ApiError sendError(String message){
        return new ApiError(404,message,"api/product");
    }

    public List<ProductDto> getToSearchProducts(String value) {
        List<Product> productList=new ArrayList<>();
        for(Product product : getAllProducts()){
            if(product.getDescription().toLowerCase().contains(value.toLowerCase()) || product.getTitle().toLowerCase().contains(value.toLowerCase())){
                productList.add(product);
            }
        }
        return  productList.stream().map(x->dtoConvert.productConvert(x)).collect(Collectors.toList());
    }
}
