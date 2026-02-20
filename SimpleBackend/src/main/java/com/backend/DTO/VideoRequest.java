package com.backend.DTO;

import com.backend.Entity.Enum.AvailabilityStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoRequest {
    private String title;
    private String director;
    private String genre;
    private AvailabilityStatus status;
}
