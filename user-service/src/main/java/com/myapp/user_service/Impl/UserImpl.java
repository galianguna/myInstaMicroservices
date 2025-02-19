package com.myapp.user_service.Impl;

import com.myapp.user_service.Dto.LoginRequest;
import com.myapp.user_service.Model.User;
import com.myapp.user_service.Repo.UserRepo;
import com.myapp.user_service.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class UserImpl implements UserService {

    @Autowired
    UserRepo userRepo;


    @Override
    public ResponseEntity<String> register(User user) {
        userRepo.save(user);
        return new ResponseEntity<>("success",HttpStatus.OK);
    }

    @Override
    public boolean login(LoginRequest request) {

        List<User> users = getUser();
        User user = users.stream().filter(a-> (a.getUsername().equals(request.getUsername()) ||
                a.getEmail().equals(request.getEmail()))
                && a.getPassword().equals(request.getPassword())).findFirst().orElse(null);
        return user != null;
    }

    @Override
    public List<User> getUser() {
        return userRepo.findAll();
    }

    @Override
    public ResponseEntity<String> deleteAccount(LoginRequest request) {
        List<User> users = getUser();
        User user = users.stream().filter(a-> (a.getUsername().equals(request.getUsername()) ||
                a.getEmail().equals(request.getEmail()))
                && a.getPassword().equals(request.getPassword())).findFirst().orElse(null);

        if(user == null)
            return new ResponseEntity<>("user not exist",HttpStatus.OK);
        userRepo.delete(user);
        return new ResponseEntity<>("success",HttpStatus.OK);
    }
}
