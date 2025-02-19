package com.myapp.post_service.Impl;

import com.myapp.post_service.ApiResponse;
import com.myapp.post_service.Dto.PostResponse;
import com.myapp.post_service.Model.Post;
import com.myapp.post_service.Model.User;
import com.myapp.post_service.Repo.PostRepo;
import com.myapp.post_service.Service.PostService;
import com.myapp.post_service.UserClient;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostImpl implements PostService {

    @Autowired
    UserClient userClient;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    PostRepo postRepo;

    @Override
    public boolean createPost(Post post) {
        postRepo.save(post);
        return true;
    }

    @Override
    public ApiResponse<List<PostResponse>> getAllPost() {

        List<PostResponse> postResponses;
        try {
            List<User> users = userClient.getUser();

            postResponses = users.stream().map(user -> {
                PostResponse postResponse = new PostResponse();

                postResponse.setId(user.getId());
                postResponse.setUsername(user.getUsername());
                postResponse.setEmail(user.getEmail());

                List<Post> postList = postRepo.findAll().stream().filter(post ->
                        post.getUserId().equals(user.getId())).toList();

                postResponse.setPostList(postList);
                return postResponse;
            }).collect(Collectors.toList());

        } catch (FeignException.ServiceUnavailable e) {
            return new ApiResponse<>(true, null, HttpStatus.SERVICE_UNAVAILABLE.value(), e.getMessage());
        } catch (Exception e) {
            return new ApiResponse<>(true, null, HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        }
        return new ApiResponse<>(false, postResponses, HttpStatus.OK.value(), "Success");
    }
    @Override
    public ApiResponse<PostResponse> getPost(Long id) {

        List<User> users = null;
        PostResponse postResponse;
        try {
            ResponseEntity<List<User>> response = restTemplate.exchange(
                    "http://USER-SERVICE/Insta/user/getUser",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<User>>() {
                    }
            );
            users = response.getBody();

            List<Post> postDetails = postRepo.findAll().stream().filter(post -> post.getUserId().equals(id)).toList();
            User userDetails = users.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);

            postResponse = new PostResponse();
            if (userDetails != null) {
                postResponse.setEmail(userDetails.getEmail());
                postResponse.setId(userDetails.getId());
                postResponse.setUsername(userDetails.getUsername());
            }
            postResponse.setPostList(postDetails);

        } catch (RestClientException e) {
            return new ApiResponse<>(true, null, HttpStatus.SERVICE_UNAVAILABLE.value(), e.getMessage());
        } catch (Exception e) {
            return new ApiResponse<>(true, null, HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        }

        return new ApiResponse<>(false, postResponse, HttpStatus.OK.value(), "Success");
    }

    @Override
    public String deletePost(Long id) {
        Optional<Post> post = postRepo.findById(id);
        if(post.isPresent()){
            postRepo.deleteById(id);
            return post.get().getContent();
        }else{
            return null;
        }
    }

}
