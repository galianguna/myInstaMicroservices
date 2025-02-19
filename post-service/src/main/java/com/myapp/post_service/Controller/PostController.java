package com.myapp.post_service.Controller;

import com.myapp.post_service.ApiResponse;
import com.myapp.post_service.Dto.PostResponse;
import com.myapp.post_service.Messaging.PostMessageProducer;
import com.myapp.post_service.Model.Post;
import com.myapp.post_service.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    PostMessageProducer postMessageProducer;

    @PostMapping
    public ApiResponse<String> createPost(@RequestBody Post post){

        boolean b = postService.createPost(post);
        if(b){
            try {
                postMessageProducer.sendMessage(post);
            }catch (Exception e){
                return new ApiResponse<>(false, null, HttpStatus.OK.value(), "Message client error :"+ e.getLocalizedMessage());
            }
            return new ApiResponse<>(false, null, HttpStatus.OK.value(), "Success");
        }else{
            return new ApiResponse<>(true, null, HttpStatus.INTERNAL_SERVER_ERROR.value(),null);
        }
    }

    @GetMapping("/delete/{id}")
    public ApiResponse<String> deletePost(@PathVariable Long id){
        String content = postService.deletePost(id);
        if(null != content){
            postMessageProducer.sendStringMessage(content+" is deleted");
            return new ApiResponse<>(false, null, HttpStatus.OK.value(), "Deleted");
        }else{
            return new ApiResponse<>(true, null, HttpStatus.INTERNAL_SERVER_ERROR.value(),null);
        }
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<List<PostResponse>>> getAllPost(){

        ApiResponse<List<PostResponse>> response = postService.getAllPost();
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatus()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PostResponse>> getPost(@PathVariable Long id){

        ApiResponse<PostResponse> response = postService.getPost(id);
        return new ResponseEntity<>(postService.getPost(id), HttpStatusCode.valueOf(response.getStatus()));
    }
}
