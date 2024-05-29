package com.codeElevate.ServiceBookingSystem.controller;

import com.codeElevate.ServiceBookingSystem.dto.AdDto;
import com.codeElevate.ServiceBookingSystem.services.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @PostMapping("/ad/{userId}")
    public ResponseEntity<?> postAd(@PathVariable Long userId, @ModelAttribute AdDto adDto) throws IOException {
        boolean success = companyService.postAd(userId,adDto);
        if(success){
            System.out.println("Posted new ad with success");
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        else {
            System.out.println("Ad not posted");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
@GetMapping("/ads/{userId}")
    public ResponseEntity<?> getAllAdsByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(companyService.getAllAds(userId));
    }

    @GetMapping("/ad/{adId}")
    public ResponseEntity<?> getAdById(@PathVariable Long adId){
        AdDto adDto = companyService.getAdById(adId);
        if(adDto!= null){
            return ResponseEntity.ok(adDto);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/ad/{adId}")
    public ResponseEntity<?> deleteAd(@PathVariable Long adId){
        boolean success = companyService.deleteAd(adId);
        if(success){
            return ResponseEntity.status(HttpStatus.OK).build();
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
