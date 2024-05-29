package com.codeElevate.ServiceBookingSystem.services.company;

import com.codeElevate.ServiceBookingSystem.dto.AdDto;

import java.io.IOException;

public interface CompanyService {

    boolean postAd(Long userId, AdDto adDto) throws IOException;
}
