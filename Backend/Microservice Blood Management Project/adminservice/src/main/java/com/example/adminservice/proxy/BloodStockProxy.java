package com.example.adminservice.proxy;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BloodStockProxy {
    private Long id;

    @NotBlank(message = "Blood group is required")
    @Pattern(regexp = "^(A|B|AB|O)[+-]$",
            message = "Invalid blood group")
    private String bloodGrp;

    private Long unitsAvailable;
    private LocalDateTime lastUpdated;
}

