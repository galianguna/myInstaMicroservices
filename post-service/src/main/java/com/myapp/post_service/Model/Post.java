package com.myapp.post_service.Model;

import jakarta.persistence.*;
@Entity
@Table(name = "posts")
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    private String content;
    private Long userId;

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
