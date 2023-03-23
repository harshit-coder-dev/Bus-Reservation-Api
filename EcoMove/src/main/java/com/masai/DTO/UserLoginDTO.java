package com.masai.DTO;

import jakarta.validation.constraints.Pattern;
import lombok.*;


import jakarta.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDTO {

    @NotNull(message = "Mobile number should not be null")
    @Pattern(regexp = "[6789]{1}[0-9]{9}", message = "Enter valid 10 digit mobile number")
    private String mobileNumber;

    @NotNull(message = "password cannot be null")
    @Pattern(regexp = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}", message = "Password must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters")
    private String password;
}
