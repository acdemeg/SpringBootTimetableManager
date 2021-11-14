package com.vkrylov.springboottimetable.rest;

import com.vkrylov.springboottimetable.entities.Order;
import com.vkrylov.springboottimetable.exceptions.AppException;
import com.vkrylov.springboottimetable.repositories.OrderRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class OrderRestController {
    private final OrderRepository orderRepository;

    public OrderRestController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/orders")
    public List<Order> showAllOrders(){
        return orderRepository.findAll();
    }

    @GetMapping("/orders/{id}")
    public Order getOrder(@PathVariable int id){
        return orderRepository.findById(id).orElseThrow(
                () -> new AppException("Order with id = " + id + " not found"));
    }

    @DeleteMapping("/orders/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteOrder(@PathVariable int id){
        orderRepository.deleteById(id);
    }

    @PostMapping("/orders")
    @PreAuthorize("hasAuthority('order:post') and @authComponent.hasPermission(authentication, #order.getAuthorId())")
    public Order addNewOrder(@RequestBody Order order){
        return orderRepository.save(order);
    }

    @PostMapping("/orders/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Transactional
    public Order updateOrder(@PathVariable int id, @RequestBody Order order) {
        Optional<Order> obj = orderRepository.findById(id);
        Order updatedOrder = obj.orElseThrow(
                () -> new AppException("Order with id = " + id + " not found"));
        updatedOrder.setStatus(order.getStatus());
        return updatedOrder;
    }

    @GetMapping("/users/{id}/orders")
    public List<Order> getOrdersByUserId(@PathVariable int id){
        return orderRepository.findAllByAuthorId(id);
    }
}
