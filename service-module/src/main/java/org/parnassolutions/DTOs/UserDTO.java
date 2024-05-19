package org.parnassolutions.DTOs;

import lombok.Data;
import org.parnassolutions.Enums.Role;

@Data
public class UserDTO {
    private String name;
    private String email;
    private String password;
    private Role role;
}
