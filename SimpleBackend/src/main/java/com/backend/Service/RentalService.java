package com.backend.Service;

import com.backend.DTO.RentalRequest;
import com.backend.Entity.Rental;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface RentalService {
    Rental bookRental(RentalRequest rentalRequest) throws BadRequestException;
    Rental returnRental(RentalRequest rentalRequest);
    List<Rental> getRentals(Long userId);
    List<Rental> getActiveRentals(Long userId);
}
