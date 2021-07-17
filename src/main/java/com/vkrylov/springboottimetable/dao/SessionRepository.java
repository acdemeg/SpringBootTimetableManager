package com.vkrylov.springboottimetable.dao;

import com.vkrylov.springboottimetable.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Integer> {
}
