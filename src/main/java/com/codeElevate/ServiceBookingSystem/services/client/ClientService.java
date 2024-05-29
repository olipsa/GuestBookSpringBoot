package com.codeElevate.ServiceBookingSystem.services.client;

import com.codeElevate.ServiceBookingSystem.dto.AdDetailsForClientDTO;
import com.codeElevate.ServiceBookingSystem.dto.AdDto;
import com.codeElevate.ServiceBookingSystem.dto.ReservationDTO;

import java.util.List;

public interface ClientService {
    List<AdDto> getAllAds();

    List<AdDto> searchAdByName(String name);
    boolean bookService(ReservationDTO reservationDTO);

    AdDetailsForClientDTO getAdDetailsByAdId(Long adId);

    List<ReservationDTO> getAllBookingsByUserId(Long userId);
}
