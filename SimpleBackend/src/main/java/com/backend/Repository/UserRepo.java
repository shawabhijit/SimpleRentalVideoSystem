package com.backend.Repository;

import com.backend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User , Long> {
    User findByEmail(String email);
}
