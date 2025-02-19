package com.myapp.user_service.Controller;

import com.myapp.user_service.Dto.LoginRequest;
import com.myapp.user_service.Model.User;
import com.myapp.user_service.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping()
    public String hello(){
        return "Welcome";
    }

    @GetMapping("/getUser")
    public List<User> getUser(){
        return userService.getUser();
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        boolean login = userService.login(request);
        if(login)
            return ResponseEntity.ok("success");
        return ResponseEntity.ok("enter valid credentials");
    }

    @PostMapping("/deleteAccount")
    public ResponseEntity<String> deleteAccount(@RequestBody LoginRequest request)  {
        return userService.deleteAccount(request);
    }
}
