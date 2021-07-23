package com.vkrylov.springboottimetable.dao;

import com.vkrylov.springboottimetable.entity.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface AttributeRepository extends JpaRepository<Attribute, Integer> {
}
