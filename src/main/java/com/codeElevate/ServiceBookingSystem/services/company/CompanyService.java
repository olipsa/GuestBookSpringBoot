package com.codeElevate.ServiceBookingSystem.services.company;

import com.codeElevate.ServiceBookingSystem.dto.AdDto;
import com.codeElevate.ServiceBookingSystem.dto.ReservationDTO;

import java.io.IOException;
import java.util.List;

public interface CompanyService {

    boolean postAd(Long userId, AdDto adDto) throws IOException;

    List<AdDto> getAllAds(Long userId);

    AdDto getAdById(Long adId);

    boolean deleteAd(Long adId);

    List<ReservationDTO> getAllAdBookings(Long companyId);

    boolean changeBookingStatus(Long bookingId, String status);
}
