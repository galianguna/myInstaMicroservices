package com.myapp.post_service.Dto;

import com.myapp.post_service.Model.Post;
import com.myapp.post_service.Model.User;

import java.util.List;

public class PostResponse {

    private Long id;
    private String username;
    private String email;
    private List<Post> postList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }
}
