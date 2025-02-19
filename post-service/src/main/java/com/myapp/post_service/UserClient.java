package com.myapp.post_service;

import com.myapp.post_service.Model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "USER-SERVICE",url = "${user-server.url}")
public interface UserClient {

    @GetMapping("/Insta/user/getUser")
    List<User>  getUser();
}
