package com.example.bloodmanagementproject.proxy;

import com.example.bloodmanagementproject.domain.Hospital;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BloodRequestProxy {
    private Long id;
    private String bloodGrp;
    private Long quantity;
    private LocalDate requestDate;
    private String status;
    private Hospital hospital;       // used by HospitalServiceImpl
    private String hospitalName;     // used by admin blood-request table
}
