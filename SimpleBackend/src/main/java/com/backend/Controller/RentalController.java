package com.backend.Controller;

import com.backend.DTO.RentalRequest;
import com.backend.Entity.Rental;
import com.backend.Service.RentalService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RentalController {

    private final RentalService rentalService;

    @PostMapping("/videos/{videoId}/rent")
    public ResponseEntity<Rental> rentVideo(
            @PathVariable Long videoId,
            @RequestParam Long userId
    ) throws BadRequestException {
        RentalRequest rentalRequest = new RentalRequest(userId, videoId);
        return ResponseEntity.ok().body(rentalService.bookRental(rentalRequest));
    }

    @PostMapping("/books/{videoId}/return")
    public ResponseEntity<Rental> returnVideo(
            @PathVariable Long videoId,
            @RequestParam Long userId
    ) {
        RentalRequest rentalRequest = new RentalRequest(userId, videoId);
        return ResponseEntity.ok().body(rentalService.returnRental(rentalRequest));
    }


}
