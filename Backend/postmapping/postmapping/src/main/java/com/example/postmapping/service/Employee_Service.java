package com.example.postmapping.service;

import com.example.postmapping.employee.Employee;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class Employee_Service {
    List<Employee> emp = new ArrayList<>();

    public String setData(Employee e){

        emp.add(e);
        return "Add Data Successfully";
    }

    public List<Employee> getData(){
        return  emp;
    }

    public Employee getDataById(Long id){
        return emp.stream().filter(x->x.getId()==id).findFirst().orElse(new Employee(null,null,null));
    }

    public String removeData(){
        emp.clear();
        return "Remove all data successfully";
    }

    public String removeDataByid(Long id){
        boolean isRemove = emp.removeIf(a -> a.getId() == id);
        if(isRemove){
            return "Data with Id " + id + " Successfully removed";
        }
        else {
            return "No Data with Id " +id;
        }
    }

    public String updateData(Long id,Employee e){
        Optional<Employee> empdata = emp.stream().filter(x -> x.getId() == id).findFirst();
        if(empdata.isPresent()){
            Employee updatedemp = empdata.get();
            updatedemp.setId(e.getId());
            updatedemp.setName(e.getName());
            updatedemp.setAddress(e.getAddress());

            return "Update data successfully";
        }
        else {
            return "No Data available with id " + id;
        }
    }
}
