package com.backend.Controller;

import com.backend.DTO.VideoRequest;
import com.backend.Entity.Enum.AvailabilityStatus;
import com.backend.Entity.Video;
import com.backend.Service.VideoService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/video")
@RequiredArgsConstructor
public class VideoController {

    private final VideoService videoService;

//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<Video> createVideo(
            @AuthenticationPrincipal UserPrincipal user,
            @RequestBody VideoRequest videoRequest) throws BadRequestException {

        Video video = videoService.createVideo(videoRequest);
        return ResponseEntity.ok().body(video);
    }

    @PostMapping("/update")
    public ResponseEntity<Video> updateVideo(
            @RequestBody VideoRequest videoRequest
    ) throws BadRequestException {
        return ResponseEntity.ok().body(videoService.updateVideo(videoRequest));
    }

    @GetMapping
    public ResponseEntity<List<Video>> getAllVideos() {
        return ResponseEntity.ok().body(videoService.getAllVideos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Video> getVideoById(@PathVariable long id) throws BadRequestException {
        return ResponseEntity.ok().body(videoService.getVideoById(id));
    }

    @GetMapping("/available")
    public ResponseEntity<List<Video>> getAvailableVideosByStatus() {
        return ResponseEntity.ok().body(videoService.getAvailableVideos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVideo(@PathVariable long id) throws BadRequestException {
        videoService.deleteVideo(id);
        return ResponseEntity.ok().body("Video deleted");
    }
}
