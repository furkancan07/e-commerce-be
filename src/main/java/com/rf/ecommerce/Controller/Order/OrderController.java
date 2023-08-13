package com.rf.ecommerce.Controller.Order;

import com.rf.ecommerce.Dto.Order.OrderDto;
import com.rf.ecommerce.Entity.Order.Order;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {
    // sipariş al
    public ResponseEntity<?> createOrder(@Valid @RequestBody Order order){
        return null;
    }
    // sipariş iptal et
    public ResponseEntity<?> deleteOrder(@PathVariable Long orderId){
        return null;
    }
    // bir mağazaya ait siparişleri getirme
    public List<OrderDto> getList(@PathVariable String username){
        return null;
    }
    // sipariş durum güncelleme
    public ResponseEntity<?> updateOrderStatus(@PathVariable Long orderId){
        return null;
    }




}
