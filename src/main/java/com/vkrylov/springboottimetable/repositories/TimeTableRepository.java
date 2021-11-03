package com.vkrylov.springboottimetable.repositories;

import com.vkrylov.springboottimetable.entities.TimeTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeTableRepository extends JpaRepository<TimeTable, Integer> {
}
