package com.vkrylov.springboottimetable.dao;

import com.vkrylov.springboottimetable.entity.AttributeValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttributeValueRepository extends JpaRepository<AttributeValue, Integer> {
}
