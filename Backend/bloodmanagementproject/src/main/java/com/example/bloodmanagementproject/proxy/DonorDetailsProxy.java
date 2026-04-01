package com.example.bloodmanagementproject.proxy;

import com.example.bloodmanagementproject.domain.Users;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DonorDetailsProxy {
    private Long id;

    @NotBlank(message = "Blood Group is required")
    @Pattern(regexp = "^(A|B|AB|O)[+-]$",
            message = "Invalid blood group")
    private String bloodGrp;

    @Min(value = 18,message = "Age must be greater then 18")
    private Integer age;

    @NotBlank(message = "Gender is required")
    @Pattern(regexp = "^(Male|Female|Other)$",
            message = "Gender must be Male, Female or Other")
    private String gender;

    @NotBlank(message = "City is required")
    private String city;

    private LocalDate lastDonationDate;
    private Boolean available;

    private Users users;
}
