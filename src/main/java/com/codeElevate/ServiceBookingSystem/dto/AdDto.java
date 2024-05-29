package com.codeElevate.ServiceBookingSystem.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class AdDto {

    private Long id;

    private String serviceName;

    private String description;

    private Double price;

    // what we will get in the POST method add img from Frontend app
    private MultipartFile img;

    private byte[] returnedImg;

    private Long userId;

    private String companyName;

}
