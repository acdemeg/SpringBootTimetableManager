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

@RepositoryRestResource(exported = false)
public interface OrderRepository extends JpaRepository<Order, Integer> {
//    @Query(value =
//            "INSERT INTO orders (\"id\", \"authorId\", \"authorName\", \"startDate\", \"endDate\", \"timeTableId\") " +
//            "VALUES (DEFAULT, :autorId, :autorName, :startDate, :endDate, :status, :timeTableId,) RETURNING *",
//            nativeQuery = true)
//    Order saveOrderNative(@Param("authorId") int authorId, @Param("authorName") String authorName,
//                          @Param("startDate") ZonedDateTime startDate, @Param("endDate") ZonedDateTime endDate,
//                          @Param("status") OrderStatus status, @Param("timeTableId") int timeTableId,
//                          @Param("attributeValues") List<AttributeValue> attributeValues);
}
