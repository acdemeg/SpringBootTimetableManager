package com.vkrylov.springboottimetable.dao;

import com.vkrylov.springboottimetable.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
