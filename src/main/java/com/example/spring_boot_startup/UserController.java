package com.example.spring_boot_startup;

import com.example.spring_boot_startup.entity.UserEntity;
import com.example.spring_boot_startup.exception.ResourseNotFoundException;
import com.example.spring_boot_startup.model.Users;
import com.example.spring_boot_startup.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;
//    @GetMapping
//    public String getUsers() {
//        return "Hello Users";
//    }

    @GetMapping
    public List<UserEntity> getUsers(){
//        return Arrays.asList(
//                new Users(1L,"Ajay","ajay@gmail.com"),
//                new Users(2L,"Raja","raja@gmail.com")
//                );
       return   userRepository.findAll();
    }

    @GetMapping("/{id}")
    public UserEntity getUserById(@PathVariable Long id){
        System.out.println(id);
        return userRepository.findById(id).orElseThrow(
                () -> new ResourseNotFoundException("User not found!")
        );

    }
    @PostMapping
    public UserEntity addUser(@RequestBody UserEntity body){
        System.out.println("user add called");
        System.out.println(body.getName()+ " " + body.getEmail());
        return userRepository.save(body);
    }

    @PutMapping("/{id}")
    public UserEntity updateUser(@RequestBody UserEntity body ,@PathVariable Long id){
        System.out.println(body.getName());
        System.out.println(id);
        UserEntity userData = userRepository.findById(id).orElseThrow(()-> new ResourseNotFoundException("User not found"));
        userData.setEmail(body.getEmail());
        userData.setName(body.getName());
        return userRepository.save(userData);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        UserEntity userData = userRepository.findById(id).orElseThrow(()-> new ResourseNotFoundException("User not found"));
        userRepository.delete(userData);
        return ResponseEntity.ok().build();
    }
}