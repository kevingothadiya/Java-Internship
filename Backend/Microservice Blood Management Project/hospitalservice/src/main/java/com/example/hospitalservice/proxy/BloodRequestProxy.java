package com.example.hospitalservice.proxy;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BloodRequestProxy {
    private Long id;

    @NotBlank(message = "Blood group is required")
    @Pattern(regexp = "^(A|B|AB|O)[+-]$",
            message = "Invalid blood group")
    private String bloodGrp;

    private Long quantity;
    private LocalDate requestDate;
    private String status;

    private HospitalProxy hospital;
}

