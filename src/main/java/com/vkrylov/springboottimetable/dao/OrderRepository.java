package com.vkrylov.springboottimetable.dao;

import com.vkrylov.springboottimetable.entity.AttributeValue;
import com.vkrylov.springboottimetable.entity.Order;
import com.vkrylov.springboottimetable.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.ZonedDateTime;
import java.util.List;

//@RepositoryRestResource(exported = false)
public interface OrderRepository extends JpaRepository<Order, Integer> {
    //@Query(value = "update public.orders set status='CANCELED'::enum_Orders_status where id=:id", nativeQuery = true)
    //void updateOrderById(@Param("id") int id);
    //@Param("status") OrderStatus status,
}
