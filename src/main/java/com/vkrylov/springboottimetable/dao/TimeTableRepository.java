package com.vkrylov.springboottimetable.dao;

import com.vkrylov.springboottimetable.entity.TimeTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeTableRepository extends JpaRepository<TimeTable, Integer> {
}
