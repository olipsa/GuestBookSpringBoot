package com.codeElevate.ServiceBookingSystem.services.company;

import com.codeElevate.ServiceBookingSystem.dto.AdDto;
import com.codeElevate.ServiceBookingSystem.dto.ReservationDTO;
import com.codeElevate.ServiceBookingSystem.entity.Ad;
import com.codeElevate.ServiceBookingSystem.entity.Reservation;
import com.codeElevate.ServiceBookingSystem.entity.User;
import com.codeElevate.ServiceBookingSystem.enums.ReservationStatus;
import com.codeElevate.ServiceBookingSystem.repository.AdRepository;
import com.codeElevate.ServiceBookingSystem.repository.ReservationRepository;
import com.codeElevate.ServiceBookingSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdRepository adRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    public boolean postAd(Long userId, AdDto adDto) throws IOException {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            Ad ad = new Ad();
            ad.setServiceName(adDto.getServiceName());
            ad.setDescription(adDto.getDescription());
            ad.setImg(adDto.getImg().getBytes());
            ad.setPrice(adDto.getPrice());
            ad.setUser(optionalUser.get());

            adRepository.save(ad);
            return true;
        }

        return false;
    }

    public List<AdDto> getAllAds(Long userId){
        return adRepository.findAllByUserId(userId).stream().map(Ad::getAdDto).collect(Collectors.toList());
    }

    public AdDto getAdById(Long adId){
        Optional<Ad> optionalAd = adRepository.findById(adId);
        if(optionalAd.isPresent()){
            return optionalAd.get().getAdDto();
        }
        return null;
    }

    public boolean deleteAd(Long adId){
        Optional<Ad> optionalAd = adRepository.findById(adId);
        if(optionalAd.isPresent()) {
            adRepository.delete(optionalAd.get());
            return true;
        }
        return false;
    }

    public List<ReservationDTO> getAllAdBookings(Long companyId){
        return reservationRepository.findAllByCompanyId(companyId)
                .stream().map(Reservation::getReservationDto).collect(Collectors.toList());
    }

    public boolean changeBookingStatus(Long bookingId, String status){
        Optional<Reservation> optionalReservation = reservationRepository.findById(bookingId);
        if(optionalReservation.isPresent()){
            Reservation existingReservation = optionalReservation.get();
            if(Objects.equals(status,"Approve")){
                existingReservation.setReservationStatus(ReservationStatus.APPROVED);
            }else{
                existingReservation.setReservationStatus(ReservationStatus.REJECTED);
            }

            reservationRepository.save(existingReservation);
            return true;
        }
        return false;
    }

}
