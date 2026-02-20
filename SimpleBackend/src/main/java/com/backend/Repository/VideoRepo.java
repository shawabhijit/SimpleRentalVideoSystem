package com.backend.Repository;

import com.backend.Entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepo extends JpaRepository<Video , Long> {
}
