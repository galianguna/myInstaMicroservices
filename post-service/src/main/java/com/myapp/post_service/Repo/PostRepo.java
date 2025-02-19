package com.myapp.post_service.Repo;

import com.myapp.post_service.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post,Long> {
}
