package com.codeElevate.ServiceBookingSystem.services.authentication;

import com.codeElevate.ServiceBookingSystem.dto.SignupRequestDTO;
import com.codeElevate.ServiceBookingSystem.dto.UserDto;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthService {

    UserDto signupClient(SignupRequestDTO signupRequestDTO);
    Boolean presentByEmail(String email);

    UserDto signupCompany(SignupRequestDTO signupRequestDTO);
}
