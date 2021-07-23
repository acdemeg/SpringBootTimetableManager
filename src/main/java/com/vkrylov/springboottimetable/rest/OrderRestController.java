package com.vkrylov.springboottimetable.rest;

import com.vkrylov.springboottimetable.dao.OrderRepository;
import com.vkrylov.springboottimetable.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class OrderRestController {
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/orders")
    public List<Order> showAllOrders(){
        return orderRepository.findAll();
    }

    @GetMapping("/orders/{id}")
    public Order getOrder(@PathVariable int id){
        Optional<Order> order = orderRepository.findById(id);
        return order.orElse(new Order());
    }

    @DeleteMapping("/orders/{id}")
    public void deleteOrder(@PathVariable int id){
        orderRepository.deleteById(id);
    }

    @PostMapping("/orders")
    public Order addNewOrder(@RequestBody Order order){
        return orderRepository.save(order);
    }

    @PatchMapping("/orders/{id}")
    public Order updateOrder(@PathVariable int id, @RequestBody Order order){
        Order obj = orderRepository.getById(id);
        obj.setStatus(order.getStatus());
        return obj;
    }
}
