package com.vkrylov.springboottimetable.repositories;

import com.vkrylov.springboottimetable.entities.AttributeValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface AttributeValueRepository extends JpaRepository<AttributeValue, Integer> {
}


