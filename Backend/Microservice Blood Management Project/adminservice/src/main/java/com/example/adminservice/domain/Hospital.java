package com.example.adminservice.domain;

import lombok.Data;

@Data
public class Hospital {
    private Long id;
    private String hospitalName;
    private String address;
    private String contactNum;
    private String licenceNumber;

    private Long userId;
}
