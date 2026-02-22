package com.backend.Service;

import com.backend.DTO.VideoRequest;
import com.backend.Entity.Video;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface VideoService {
    Video createVideo(VideoRequest request) throws BadRequestException;
    Video updateVideo(VideoRequest request) throws BadRequestException;
    List<Video> getAllVideos();
    Video getVideoById(long id) throws BadRequestException;
    void deleteVideo(long id) throws BadRequestException;
    List<Video> getAvailableVideos();
}
