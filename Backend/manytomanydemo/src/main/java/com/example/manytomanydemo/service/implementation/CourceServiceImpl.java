package com.example.manytomanydemo.service.implementation;

import com.example.manytomanydemo.dto.CourceDto;
import com.example.manytomanydemo.entity.Cource;
import com.example.manytomanydemo.entity.Student;
import com.example.manytomanydemo.repository.CourceRepo;
import com.example.manytomanydemo.service.CourceService;
import com.example.manytomanydemo.utility.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourceServiceImpl implements CourceService {

    @Autowired
    private CourceRepo courceRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public String saveAll(CourceDto courceDto) {
        Cource cource = modelMapper.getCourceEntity(courceDto);
//        cource.getStudents().forEach(x->x.setCources(cource));
        courceRepo.save(cource);

        return "Successfully Saved";
    }

    @Override
    public List<CourceDto> getAll() {
        List<Cource> all = courceRepo.findAll();
        return modelMapper.getListCourceDto(all);
    }

    @Override
    public List<CourceDto> getByCourceTitle(String title) {
        List<Cource> byTitle = courceRepo.findByTitle(title);
        return modelMapper.getListCourceDto(byTitle);
    }
}
