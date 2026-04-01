package com.example.bloodmanagementproject.proxy;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProxy {
    private Long id;

    @NotBlank(message = "Name is Required")
    private String name;

    @NotBlank(message = "Email is Required")
    @Email(message = "Invalid Email Format")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Password is Required")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password must contain at least 8 characters, one uppercase, one lowercase, one number and one special character")
    private String password;

    @NotBlank(message = "Role is Required")
    private String role;

    @NotBlank(message = "Phone number is Required")
    @Pattern(regexp = "^[0-9]{10}$",
            message = "Phone number must be exactly 10 digits")
    private String phoneNum;

    @NotBlank(message = "Status is Required")
    private String status;
}
