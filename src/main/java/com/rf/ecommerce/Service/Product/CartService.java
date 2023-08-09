package com.rf.ecommerce.Service.Product;

import com.rf.ecommerce.Entity.Product.Cart;
import com.rf.ecommerce.Repository.Product.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    private CartRepository cartRepository;

@Autowired
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }
    public List<Cart> getAllHampers(){
    return  cartRepository.findAll();
    }
    public boolean existsById(Long id){
    return cartRepository.existsById(id);
    }
    public Cart findById(Long id){
    return cartRepository.findById(id).orElseThrow();
    }
    public void save(Cart cart){
    cartRepository.save(cart);
    }
    public void delete(Long id){
    cartRepository.deleteById(id);
    }
}
