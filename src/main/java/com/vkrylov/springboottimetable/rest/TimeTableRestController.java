package com.vkrylov.springboottimetable.rest;

import com.vkrylov.springboottimetable.entities.TimeTable;
import com.vkrylov.springboottimetable.exceptions.AppException;
import com.vkrylov.springboottimetable.repositories.TimeTableRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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
    @PreAuthorize("hasAuthority('timetable:delete')")
    public void deleteTimeTable(@PathVariable int id){
        timeTableRepository.deleteById(id);
    }

    @PostMapping("/timetables")
    @PreAuthorize("hasAuthority('timetable:post')")
    public TimeTable createNewTimeTable(@RequestBody TimeTable timeTable){
        return timeTableRepository.save(timeTable);
    }

    @PostMapping("/timetables/{id}")
    @Transactional
    @PreAuthorize("hasAuthority('timetable:post')")
    public TimeTable updateTimeTableTitle(@PathVariable int id, @RequestBody TimeTable timeTable){
        Optional<TimeTable> obj = timeTableRepository.findById(id);
        TimeTable updatedTimeTable = obj.orElseThrow(
                () -> new AppException("TimeTable with id = " + id + " not found"));
        updatedTimeTable.setTitle(timeTable.getTitle());
        return updatedTimeTable;
    }

}
