package com.backend.Service.impl;

import com.backend.DTO.RentalRequest;
import com.backend.Entity.Enum.AvailabilityStatus;
import com.backend.Entity.Rental;
import com.backend.Entity.User;
import com.backend.Entity.Video;
import com.backend.Repository.RentalRepo;
import com.backend.Repository.UserRepo;
import com.backend.Repository.VideoRepo;
import com.backend.Service.RentalService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService {

    private final RentalRepo rentalRepo;
    private final UserRepo userRepo;
    private final VideoRepo videoRepo;

    @Override
    @Transactional
    public Rental bookRental(RentalRequest rentalRequest) throws BadRequestException {
        User user =  userRepo.findById(rentalRequest.getUserId()).orElseThrow(
                () -> new RuntimeException("User not found")
        );

        Video video = videoRepo.findById(rentalRequest.getVideoId()).orElseThrow(
                () -> new RuntimeException("Video not found")
        );

        if(rentalRepo.countByUserIdAndActiveTrue(user.getId()) >= 2) {
            throw new BadRequestException("You already rented two videos.");
        }

        if (video.getStatus() != (AvailabilityStatus.AVAILABLE)) {
            throw new BadRequestException("This video is not available.");
        }

        video.setStatus(AvailabilityStatus.UNAVAILABLE);
        video = videoRepo.save(video);

        Rental rental = Rental.builder()
                .user(user)
                .video(video)
                .active(true)
                .rentedAt(LocalDateTime.now())
                .build();

        return rentalRepo.save(rental);
    }

    @Override
    public Rental returnRental(RentalRequest rentalRequest) {

        Rental rental = rentalRepo.findByUserIdAndVideoIdAndActiveTrue(
                rentalRequest.getUserId() , rentalRequest.getVideoId()
        ).orElseThrow(
                () -> new RuntimeException("Active rental not found.")
        );

        rental.setActive(false);
        rental.setReturnedAt(LocalDateTime.now());

        Video video = rental.getVideo();
        video.setStatus(AvailabilityStatus.AVAILABLE);

        videoRepo.save(video);

        return rentalRepo.save(rental);
    }

    @Override
    public List<Rental> getRentals(Long userId) {
        return rentalRepo.findByUserId(userId);
    }

    @Override
    public List<Rental> getActiveRentals(Long userId) {
        return rentalRepo.findByUserIdAndActiveTrue(userId);
    }
}
