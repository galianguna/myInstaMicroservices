package com.myapp.user_service.Service;

import com.myapp.user_service.Dto.LoginRequest;
import com.myapp.user_service.Model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    ResponseEntity<String> register(User user);

    boolean login(LoginRequest request);

    List<User> getUser();

    ResponseEntity<String> deleteAccount(LoginRequest request);
}
