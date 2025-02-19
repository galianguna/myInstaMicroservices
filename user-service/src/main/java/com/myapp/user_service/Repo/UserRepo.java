package com.myapp.user_service.Repo;

import com.myapp.user_service.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {

}
