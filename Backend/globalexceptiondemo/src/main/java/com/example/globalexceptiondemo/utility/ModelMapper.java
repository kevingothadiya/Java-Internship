package com.example.globalexceptiondemo.utility;

import com.example.globalexceptiondemo.entity.Address;
import com.example.globalexceptiondemo.entity.Student;
import com.example.globalexceptiondemo.proxy.AddressProxy;
import com.example.globalexceptiondemo.proxy.StudentProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@Component
public class ModelMapper {

    @Autowired
    private ObjectMapper objectMapper;

    public Student getStudentEntity(StudentProxy studentProxy){
        return objectMapper.convertValue(studentProxy, Student.class);
    }

    public StudentProxy getStudentProxy(Student student){
        student.getAddress().forEach(x->x.setStudent(null));
        return objectMapper.convertValue(student, StudentProxy.class);
    }

    public List<Student> getListStudentEntity(List<StudentProxy> studentProxies){
        return studentProxies.stream().map(this::getStudentEntity).toList();
    }

    public List<StudentProxy> getListStudentProxy(List<Student> students){
        return students.stream().map(this::getStudentProxy).toList();
    }

    public Address getAddressEntity(AddressProxy addressProxy){
        return objectMapper.convertValue(addressProxy,Address.class);
    }

    public AddressProxy getAddressProxy(Address address){
        address.getStudent().setAddress(null);
        return objectMapper.convertValue(address,AddressProxy.class);
    }

    public List<Address> getListAddressEntity(List<AddressProxy> addressProxies){
        return addressProxies.stream().map(this::getAddressEntity).toList();
    }

    public List<AddressProxy> getListAddressProxy(List<Address> addresses){
        return addresses.stream().map(this::getAddressProxy).toList();
    }
}
