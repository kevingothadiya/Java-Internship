package com.example.manytomanydemo.service;

import com.example.manytomanydemo.dto.CourceDto;

import java.util.List;

public interface CourceService {

    String saveAll(CourceDto courceDto);

    List<CourceDto> getAll();

    List<CourceDto> getByCourceTitle(String title);
}
