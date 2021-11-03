package com.vkrylov.springboottimetable.repositories;

import com.vkrylov.springboottimetable.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findAllByAuthorId(int id);
}
