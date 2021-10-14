package com.vkrylov.springboottimetable.rest;

import com.vkrylov.springboottimetable.dao.OrderRepository;
import com.vkrylov.springboottimetable.entity.Order;
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
    @Transactional
    public Order updateOrder(@PathVariable int id, @RequestBody Order order) throws Exception {
        Optional<Order> obj = orderRepository.findById(id);
        Order updatedOrder = obj.orElseThrow(Exception::new);
        updatedOrder.setStatus(order.getStatus());
        return updatedOrder;
    }

    @GetMapping("/users/{id}/orders")
    public List<Order> getOrdersByUserId(@PathVariable int id){
        return orderRepository.findAllByAuthorId(id);
    }
}
