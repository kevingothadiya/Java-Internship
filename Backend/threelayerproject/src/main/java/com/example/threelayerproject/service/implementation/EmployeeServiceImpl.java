package com.example.threelayerproject.service.implementation;

import com.example.threelayerproject.model.Employee;
import com.example.threelayerproject.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    List<Employee> empData = new ArrayList<>();

    @Override
    public String saveData(Employee emp) {
        boolean b = empData.stream().anyMatch(a -> a.getId() == emp.getId());
        if(b){
            return "Data Has been all ready exist";
        }

        empData.add(emp);
        return "Successfully Added";
    }

    @Override
    public List<Employee> getAllData() {
        if(empData.isEmpty()){
             empData.add(new Employee(null,null,null));
             return empData;
        }
        return empData;
    }

    @Override
    public Employee getById(Long id) {
        Optional<Employee> byId = empData.stream().filter(x -> x.getId() == id).findFirst();
        if(byId.isPresent()){
            return empData.stream().filter(x -> x.getId() == id).findFirst().get();
        }
        else {
            return new Employee(null,null,null);
        }
    }

    @Override
    public String updateData(Employee emp, Long id) {
        Optional<Employee> update = empData.stream().filter(y -> y.getId() == id).findFirst();
        if(update.isPresent()) {
            Employee employee = update.get();
            employee.setId(emp.getId());
            employee.setName(emp.getName());
            employee.setAddress(emp.getAddress());

            return "SuccessFully Updated";
        }

            return "Employee Data With ID " + id + " is not Available";
    }

    @Override
    public String removeById(Long id) {
        boolean isRemove = empData.removeIf(a -> a.getId() == id);
        if(isRemove){
            return "Employee Data with ID " + id + " is successfully Removed";
        }
        return "Employee Data with ID " + id + "is not Available";
    }

    @Override
    public String removeAllData() {
        if(empData.isEmpty()){
            return "There is no Employee Data";
        }
        else {
            empData.clear();
            return "Removed Successfully";
        }
    }
}
