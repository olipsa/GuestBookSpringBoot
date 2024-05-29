package com.codeElevate.ServiceBookingSystem.services.client;

import com.codeElevate.ServiceBookingSystem.dto.AdDetailsForClientDTO;
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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService{

    @Autowired
    private AdRepository adRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    public List<AdDto> getAllAds(){
        return adRepository.findAll().stream().map(Ad::getAdDto).collect(Collectors.toList());
    }

    public List<AdDto> searchAdByName(String name){
        return adRepository.findAllByServiceNameContaining(name).stream().map(Ad::getAdDto).collect(Collectors.toList());
    }

    public boolean bookService(ReservationDTO reservationDTO){
        Optional<Ad> optionalAd = adRepository.findById(reservationDTO.getAdId());
        Optional<User> optionalUser = userRepository.findById(reservationDTO.getUserId());

        if(optionalAd.isPresent() && optionalUser.isPresent()){
            Reservation reservation = new Reservation();
            reservation.setBookDate(reservationDTO.getBookDate());
            reservation.setReservationStatus(ReservationStatus.PENDING);
            reservation.setUser(optionalUser.get());

            reservation.setAd(optionalAd.get());
            reservation.setCompany(optionalAd.get().getUser());

            reservationRepository.save(reservation);
            return true;
        }
        return false;
    }

    public AdDetailsForClientDTO getAdDetailsByAdId(Long adId){
        Optional<Ad> optionalAd = adRepository.findById(adId);
        AdDetailsForClientDTO adDetailsForClientDTO = new AdDetailsForClientDTO();

        if(optionalAd.isPresent()){
            adDetailsForClientDTO.setAdDto(optionalAd.get().getAdDto());
        }
        return adDetailsForClientDTO;
    }

    public List<ReservationDTO> getAllBookingsByUserId(Long userId){
        return reservationRepository.findAllByUserId(userId).stream().map(Reservation::getReservationDto).collect(Collectors.toList());
    }
}
