package com.pinsoft.ticketwebbe.dto;

import com.pinsoft.ticketwebbe.enums.Gender;
import com.pinsoft.ticketwebbe.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String name;
    private String surname;
    private String username;
    private String email;
    private String password;
    private Role role;
    private Gender gender;
}
