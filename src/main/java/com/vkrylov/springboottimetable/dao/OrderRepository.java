package com.vkrylov.springboottimetable.dao;

import com.vkrylov.springboottimetable.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findAllByAuthorId(int id);
}
