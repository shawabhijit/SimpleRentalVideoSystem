package com.backend.Repository;

import com.backend.Entity.Enum.AvailabilityStatus;
import com.backend.Entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoRepo extends JpaRepository<Video , Long> {
    List<Video> findByStatus(AvailabilityStatus status);
}
