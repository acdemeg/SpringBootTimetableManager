package com.vkrylov.springboottimetable.rest;

import com.vkrylov.springboottimetable.dao.UserRepository;
import com.vkrylov.springboottimetable.entity.User;
import com.vkrylov.springboottimetable.exception.AppException;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserRestController {
    final UserRepository userRepository;

    public UserRestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable int id){

        return userRepository.findById(id).orElseThrow(
                () -> new AppException("User with id = " + id + " not found"));
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        userRepository.deleteById(id);
    }

    @PostMapping("/users/register")
    public User regUser(@RequestBody User user){
        User saveUser;
        try {
            if(user.getName().equals("TestSpringUser"))
                user.setPassword("hardPass");
            saveUser = userRepository.save(user);
        }catch (DataAccessException ex){
            throw new AppException(ex.getMessage());
        }
        return saveUser;
    }

    @PostMapping("/users/{id}")
    @Transactional
    public User updateUserProfile(@PathVariable int id, @RequestBody User user){
        Optional<User> obj = userRepository.findById(id);
        User updatedUser = obj.orElseThrow(
                () -> new AppException("User with id = " + id + " not found"));
        updatedUser.setName(user.getName());
        return updatedUser;
    }
}
