package com.backend.Repository;

import com.backend.Entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RentalRepo extends JpaRepository<Rental , Long> {

    Optional<Rental> findByUserIdAndVideoIdAndActiveTrue(Long userId, Long videoId);

    List<Rental> findByUserId(Long userId);

    List<Rental> findByUserIdAndActiveTrue(Long userId);

    long countByUserIdAndActiveTrue(Long userId);
}
