package com.backend.Entity;

import com.backend.Entity.Enum.AvailabilityStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "videos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String director;
    private String genre;
    @Enumerated(EnumType.STRING)
    private AvailabilityStatus status;

    @OneToMany(mappedBy = "video")
    private List<Rental> rentals;
}