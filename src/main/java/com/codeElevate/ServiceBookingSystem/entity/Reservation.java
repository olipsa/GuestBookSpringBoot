package com.codeElevate.ServiceBookingSystem.entity;

import com.codeElevate.ServiceBookingSystem.dto.ReservationDTO;
import com.codeElevate.ServiceBookingSystem.enums.ReservationStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
@Data
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private ReservationStatus reservationStatus;

    private Date bookDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE) // if user is deleted, all it's reservations get deleted
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "company_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE) // if company is deleted, all it's ads get deleted
    private User company;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ad_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Ad ad;

    public ReservationDTO getReservationDto(){
        ReservationDTO dto = new ReservationDTO();

        dto.setId(id);
        dto.setServiceName(ad.getServiceName());
        dto.setBookDate(bookDate);
        dto.setReservationStatus(reservationStatus);
        dto.setAdId(ad.getId());
        dto.setCompanyId(company.getId());
        dto.setUserName(user.getName());

        return dto;
    }

}
