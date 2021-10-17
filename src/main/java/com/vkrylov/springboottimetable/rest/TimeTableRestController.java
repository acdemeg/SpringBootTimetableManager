package com.vkrylov.springboottimetable.rest;

import com.vkrylov.springboottimetable.dao.TimeTableRepository;
import com.vkrylov.springboottimetable.entity.TimeTable;
import com.vkrylov.springboottimetable.exception.AppException;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TimeTableRestController {

    final TimeTableRepository timeTableRepository;

    public TimeTableRestController(TimeTableRepository timeTableRepository) {
        this.timeTableRepository = timeTableRepository;
    }

    @GetMapping("/timetables/{id}")
    public TimeTable getTimeTableById(@PathVariable int id){
        return timeTableRepository.findById(id).orElseThrow(
                () -> new AppException("TimeTable with id = " + id + " not found"));
    }

    @GetMapping("/timetables")
    public List<TimeTable> getAllTimeTables(){
        return timeTableRepository.findAll();
    }

    @DeleteMapping("/timetables/{id}")
    public void deleteTimeTable(@PathVariable int id){
        timeTableRepository.deleteById(id);
    }

    @PostMapping("/timetables")
    public TimeTable createNewTimeTable(@RequestBody TimeTable timeTable){
        return timeTableRepository.save(timeTable);
    }

    @PostMapping("/timetables/{id}")
    @Transactional
    public TimeTable updateTimeTableTitle(@PathVariable int id, @RequestBody TimeTable timeTable){
        Optional<TimeTable> obj = timeTableRepository.findById(id);
        TimeTable updatedTimeTable = obj.orElseThrow(
                () -> new AppException("TimeTable with id = " + id + " not found"));
        updatedTimeTable.setTitle(timeTable.getTitle());
        return updatedTimeTable;
    }

}
