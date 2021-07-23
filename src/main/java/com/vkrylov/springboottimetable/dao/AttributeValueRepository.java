package com.vkrylov.springboottimetable.dao;

import com.vkrylov.springboottimetable.entity.AttributeValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface AttributeValueRepository extends JpaRepository<AttributeValue, Integer> {
}


