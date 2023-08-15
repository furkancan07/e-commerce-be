package com.rf.ecommerce.Controller.Order;

import com.rf.ecommerce.Dto.Order.OrderDto;
import com.rf.ecommerce.Dto.Order.RequestOrderDto;
import com.rf.ecommerce.Dto.Order.UpdateStatusDto;
import com.rf.ecommerce.Entity.Order.Order;
import com.rf.ecommerce.Service.Order.OrderService;
import com.rf.ecommerce.error.ApiError;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    // sipariş al
    @PostMapping("/createOrder/{productId}/{email}")
    public ResponseEntity<?> createOrder(@Valid @RequestBody RequestOrderDto order, @PathVariable Long productId, @PathVariable String email){
        return orderService.createAtOrder(order,productId,email);
    }

    // siparişi iptal et
    @DeleteMapping("/deleteOrder/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long orderId){
       return orderService.deleteAtOrder(orderId);
    }

    // bir mağazaya ait siparişleri getirme
    @GetMapping("/getOrderList/{username}")
    public List<OrderDto> getList(@PathVariable String username){
        return orderService.getList(username);
    }

    // bir kullanıcının yaptığı sipraişleri getirme
    @GetMapping("/getMyOrderList/{email}")
    public List<OrderDto> getMyOrderLİst(@PathVariable String email){return orderService.getMyOrderList(email);}

    // sipariş durum güncelleme
    @PutMapping("/updateOrderStatus/{orderId}")
    public ResponseEntity<?> updateOrderStatus(@PathVariable Long orderId, @RequestBody UpdateStatusDto status){
        return orderService.updateStatus(orderId,status);
    }

}
