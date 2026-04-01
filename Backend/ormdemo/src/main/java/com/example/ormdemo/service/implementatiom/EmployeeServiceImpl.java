package com.example.ormdemo.service.implementatiom;

import com.example.ormdemo.entity.EmployeeEntity;
import com.example.ormdemo.repository.EmployeeRepo;
import com.example.ormdemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepo emprepo;

    @Override
    public String saveData(EmployeeEntity emp) {
        EmployeeEntity save = emprepo.save(emp);
        return save.toString();
    }

    @Override
    public List<EmployeeEntity> getAllData() {
        return emprepo.findAll();
    }

    @Override
    public EmployeeEntity getById(Long id) {
        Optional<EmployeeEntity> byId = emprepo.findById(id);
        if(byId.isEmpty()){
            return new EmployeeEntity(null,null,null);
        }
        else {
            return byId.get();
        }
    }

    @Override
    public String updateData(EmployeeEntity emp,Long id) {
        Optional<EmployeeEntity> byId = emprepo.findById(id);
        if(byId.isPresent()){
            EmployeeEntity empEntity = byId.get();
            empEntity.setId(emp.getId());
            empEntity.setName(emp.getName());
            empEntity.setAddress(emp.getAddress());
            return "Successfully Updated";
        }
        else {
            return "No Data found with ID "+id;
        }
    }

    @Override
    public String removeById(Long id) {
        Optional<EmployeeEntity> byId = emprepo.findById(id);
        if(byId.isPresent()){
            emprepo.deleteById(id);
            return "Successfully Removed Data With ID " + id;
        }
        else {
            return "No Data Found with ID " + id;
        }
    }

    @Override
    public String removeAllData() {
        emprepo.deleteAll();
        return "Successfully Removed All Data";
    }
}
