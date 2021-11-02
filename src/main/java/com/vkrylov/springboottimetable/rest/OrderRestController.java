package com.vkrylov.springboottimetable.rest;

import com.vkrylov.springboottimetable.dao.OrderRepository;
import com.vkrylov.springboottimetable.entity.Order;
import com.vkrylov.springboottimetable.exception.AppException;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
@PreAuthorize("hasAnyAuthority('order:post', 'order:delete')")
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
    public void deleteOrder(@PathVariable int id){
        orderRepository.deleteById(id);
    }

    @PostMapping("/orders")
    public Order addNewOrder(@RequestBody Order order){
        Order saveOrder;
        try {
            saveOrder = orderRepository.save(order);
        }catch (DataAccessException ex){
            throw new AppException(ex.getMessage());
        }
        return saveOrder;
    }

    @PostMapping("/orders/{id}")
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
