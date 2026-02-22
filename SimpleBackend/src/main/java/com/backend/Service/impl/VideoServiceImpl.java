package com.backend.Service.impl;

import com.backend.DTO.VideoRequest;
import com.backend.Entity.Enum.AvailabilityStatus;
import com.backend.Entity.Enum.Role;
import com.backend.Entity.User;
import com.backend.Entity.Video;
import com.backend.Repository.UserRepo;
import com.backend.Repository.VideoRepo;
import com.backend.Service.VideoService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService {

    private final VideoRepo videoRepo;
    private final UserRepo userRepo;

    @Override
    public Video createVideo(VideoRequest request) throws BadRequestException {
        // user present
        AuthenticateUser();

        if (request.getStatus() == null) {
            request.setStatus(AvailabilityStatus.AVAILABLE);
        }

        Video video = Video.builder()
                .title(request.getTitle())
                .genre(request.getGenre())
                .director(request.getDirector())
                .status(request.getStatus())
                .build();

        return videoRepo.save(video);
    }

    @Override
    public Video updateVideo(VideoRequest request) throws BadRequestException {
        AuthenticateUser();

        Video video = Video.builder()
                .title(request.getTitle())
                .genre(request.getGenre())
                .director(request.getDirector())
                .status(request.getStatus())
                .build();

        return videoRepo.save(video);
    }

    @Override
    public List<Video> getAllVideos() {
        return videoRepo.findAll();
    }

    @Override
    public Video getVideoById(long id) throws BadRequestException {
        return videoRepo.findById(id).orElseThrow(
                () -> new BadRequestException("video not found with this Id : "+id)
        );
    }

    @Override
    public void deleteVideo(long videoId) throws BadRequestException {
        AuthenticateUser();
        videoRepo.deleteById(videoId);
    }

    @Override
    public List<Video> getAvailableVideos() {
        return videoRepo.findByStatus(AvailabilityStatus.AVAILABLE);
    }

    private void AuthenticateUser () throws BadRequestException {
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        assert authentication != null;
        String email = authentication.getName();

        User user = userRepo.findByEmail(email);
        if (user == null) {
            throw new BadRequestException("user not found");
        }
        // user is admin
        if (!user.getRole().equals(Role.ADMIN)) {
            throw new BadRequestException("Only admins can create videos");
        }
    }
}
