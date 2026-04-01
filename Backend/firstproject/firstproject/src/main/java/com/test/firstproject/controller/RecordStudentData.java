package com.test.firstproject.controller;

import ch.qos.logback.core.util.DelayStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/student-data")
public class RecordStudentData {

    StudentsData s;
    List<StudentsData> d;
    @GetMapping("/set-student-data")
    public String setData(){
        d = new ArrayList<>();
        d.add(new StudentsData(1,"Kevin","Ahmedabad"));
        d.add(new StudentsData(2,"Nigam","Surat"));
        d.add(new StudentsData(3,"Parth","Vadodra"));
        d.add(new StudentsData(4,"Akshat","Gandhinagar"));
        d.add(new StudentsData(5,"Aditya","Valsad"));
        d.add(new StudentsData(6,"Dev","Anand"));
        d.add(new StudentsData(7,"Ravi","Mehsana"));
        d.add(new StudentsData(8,"Dinesh","Ahmedabad"));
        d.add(new StudentsData(9,"Chirag","Surat"));
        d.add(new StudentsData(10,"Smit","Valsad"));

        return "Successfully added";
    }

    @GetMapping("/get-student-data")
    public List<StudentsData> getData(){
        if (d == null || d.isEmpty()) {
            List<StudentsData> temp = new ArrayList<>();
            temp.add(new StudentsData(0,"no data","no address"));
            return temp;
        }
        return d;
    }

    @GetMapping("/get-perticuler-data")
    public StudentsData  getPerticulerData(){
        int id = 10;
        if(d==null || d.isEmpty()){
            return new StudentsData(0,null,null);
        }
        StudentsData s = d.stream().filter(a -> a.getId()== id).findFirst().orElse(new StudentsData(0,null,null));
        return s;
    }

    @GetMapping("/remove-perticuler-data")
    public String removePerticulerData(){
        int id = 2;
        if(d==null || d.isEmpty()){
            return "There is no data in list";
        }
        else {
            d.removeIf(a -> a.getId() == id);
            return "Remove data Successfully";
        }
    }

    @GetMapping("/remove-student-data")
    public String removeData(){
        if(d==null || d.isEmpty()){
            return "Data not found";
        }
        else {
            d.clear();
            return "Remove All data successfully";
        }
    }
}
