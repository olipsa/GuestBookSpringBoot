package com.codeElevate.ServiceBookingSystem.repository;

import com.codeElevate.ServiceBookingSystem.entity.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AdRepository extends JpaRepository<Ad, Long> {
}
