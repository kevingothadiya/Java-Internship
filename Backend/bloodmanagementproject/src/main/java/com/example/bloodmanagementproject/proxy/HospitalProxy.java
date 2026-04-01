package com.example.bloodmanagementproject.proxy;

import com.example.bloodmanagementproject.domain.Users;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HospitalProxy {
    private Long id;

    @NotBlank(message = "Hospital Name is required")
    private String hospitalName;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "Contact Number is required")
    @Pattern(regexp = "^[0-9]{10}$",
            message = "Phone number must be exactly 10 digits")
    private String contactNum;

    @NotBlank(message = "Licence Number is required")
    private String licenceNumber;

    private Users users;
}
