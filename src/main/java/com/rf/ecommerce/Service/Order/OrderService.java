package com.rf.ecommerce.Service.Order;

import com.rf.ecommerce.Dto.Order.OrderDto;
import com.rf.ecommerce.Entity.Order.Order;
import com.rf.ecommerce.Repository.Order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    public void save(Order order){orderRepository.save(order);}
    public void delete(Long orderId){orderRepository.deleteById(orderId);}
    public List<OrderDto> getLists(){return null;}

}
