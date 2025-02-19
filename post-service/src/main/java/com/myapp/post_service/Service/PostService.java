package com.myapp.post_service.Service;

import com.myapp.post_service.ApiResponse;
import com.myapp.post_service.Dto.PostResponse;
import com.myapp.post_service.Model.Post;

import java.util.List;

public interface PostService {
    boolean createPost(Post post);

    ApiResponse<List<PostResponse>> getAllPost();

    ApiResponse<PostResponse> getPost(Long id);

    String deletePost(Long id);
}
