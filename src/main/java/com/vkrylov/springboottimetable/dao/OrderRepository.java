package com.vkrylov.springboottimetable.dao;

import com.vkrylov.springboottimetable.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
