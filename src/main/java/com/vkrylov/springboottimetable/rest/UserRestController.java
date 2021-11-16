package com.vkrylov.springboottimetable.rest;

import com.vkrylov.springboottimetable.entities.User;
import com.vkrylov.springboottimetable.exceptions.AppException;
import com.vkrylov.springboottimetable.repositories.UserRepository;
import com.vkrylov.springboottimetable.services.SecurityUser;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @PostMapping("/users/login")
    @PreAuthorize("hasAuthority('USER')")
    public Optional<User> getLoginUser(Authentication auth){
        SecurityUser securityUser = (SecurityUser)auth.getPrincipal();
        return userRepository.findById(securityUser.getId());
    }

    @DeleteMapping("/users/{id}")
    @PreAuthorize("hasAuthority('user:delete')")
    public void deleteUser(@PathVariable int id){
        userRepository.deleteById(id);
    }

    @PostMapping("/users/register")
    public User regUser(@RequestBody User user){
        String password = (user.getPassword() == null) ? "HardPassword" : user.getPassword();
        user.setPassword(new BCryptPasswordEncoder(12).encode(password));
        return userRepository.save(user);
    }

    @PostMapping("/users/{id}")
    @Transactional
    @PreAuthorize("hasAuthority('user:post') and @authComponent.hasPermission(authentication, #id)")
    public User updateUserProfile(@PathVariable int id, @RequestBody User user){

        Optional<User> obj = userRepository.findById(id);
        User updatedUser = obj.orElseThrow(
                () -> new AppException("User with id = " + id + " not found"));
        updatedUser.setName(user.getName());
        return updatedUser;
    }
}
