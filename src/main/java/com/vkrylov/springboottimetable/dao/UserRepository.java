package com.vkrylov.springboottimetable.dao;

import com.vkrylov.springboottimetable.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
