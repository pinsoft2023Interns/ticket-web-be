package com.pinsoft.ticketwebbe.dto;

import com.pinsoft.ticketwebbe.enums.Gender;
import com.pinsoft.ticketwebbe.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequest {
    private Long id;
    private String username;
    private String name;
    private String surname;
    private String email;
    private String password;
    private Role role;
    private Gender gender;

}
