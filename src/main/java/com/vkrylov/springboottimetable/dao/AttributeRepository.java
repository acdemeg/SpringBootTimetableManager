package com.vkrylov.springboottimetable.dao;

import com.vkrylov.springboottimetable.entity.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttributeRepository extends JpaRepository<Attribute, Integer> {
}
