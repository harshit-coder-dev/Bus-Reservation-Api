package com.masai.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminLoginDTO {

    @NotNull(message = "Mobile number should not be null")
    @NotBlank(message = "Mobile Number should not be blank...!")
    private String mobileNumber;

    @NotNull(message="password should not be null")
    @NotBlank(message = "Password should not be blank...!")
    private String adminPassword;
}
