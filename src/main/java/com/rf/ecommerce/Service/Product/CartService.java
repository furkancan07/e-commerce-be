package com.rf.ecommerce.Service.Product;

import com.rf.ecommerce.Dto.DtoConvert;
import com.rf.ecommerce.Dto.Product.CartDto;
import com.rf.ecommerce.Entity.Product.Cart;
import com.rf.ecommerce.Entity.Product.Product;
import com.rf.ecommerce.Entity.User.User;
import com.rf.ecommerce.Repository.Product.CartRepository;
import com.rf.ecommerce.Service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {
    private CartRepository cartRepository;
    private final DtoConvert dtoConvert;
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;

@Autowired
    public CartService(CartRepository cartRepository, DtoConvert dtoConvert) {
        this.cartRepository = cartRepository;
    this.dtoConvert = dtoConvert;
}
    public List<Cart> getAllCarts(){
    return  cartRepository.findAll();
    }

    public boolean addToCart(String email,Long productId){
    if(!userService.existsByEmail(email) && !productService.existsById(productId)){
        return false;
    }
        Cart cart =new Cart();
        User user=userService.findByEmail(email);
        Product product=productService.findById(productId);
        cart.setUser(user);
        cart.setProduct(product);
        cartRepository.save(cart);
    return  true;
    }
    public boolean deleteToCart(Long cartId){
       if(!cartRepository.existsById(cartId)){
           return false;
       }
       Cart cart = cartRepository.findById(cartId).orElseThrow();
       cartRepository.deleteById(cartId);
       return true;
    }
    public List<CartDto> getCarts(String email){
        List<Cart> carts =new ArrayList<>();
        for(Cart cart : getAllCarts()){
            if(cart.getUser().getEmail().equals(email)){
                carts.add(cart);
            }
        }
        return carts.stream().map(x->dtoConvert.cartConvert(x)).collect(Collectors.toList());
    }
}
