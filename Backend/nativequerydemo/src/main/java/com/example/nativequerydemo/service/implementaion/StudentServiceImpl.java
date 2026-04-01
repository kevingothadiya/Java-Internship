package com.example.nativequerydemo.service.implementaion;

import com.example.nativequerydemo.dto.StudentDto;
import com.example.nativequerydemo.entity.Student;
import com.example.nativequerydemo.helper.MapperHelper;
import com.example.nativequerydemo.projection.FirstNameLastNameAddress;
import com.example.nativequerydemo.repository.StudentRepo;
import com.example.nativequerydemo.service.StudentService;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tools.jackson.core.PrettyPrinter;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private Faker faker;

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private MapperHelper helper;

    @Override
    public String addFakerData() {

        for (int i=0;i<100;i++) {
            Student s = new Student();
            s.setFirstName(faker.name().firstName());
            s.setLastName(faker.name().lastName());
            s.setAddress(faker.address().fullAddress());
            s.setEmailId(faker.internet().emailAddress());

            studentRepo.save(s);
        }

        return "Successfully Added";
    }

    @Override
    public List<StudentDto> getAllStudentData() {
        return helper.getListStuidentDto(studentRepo.getAllStudent());
    }

    @Override
    public StudentDto getStudentByEmailID(String email) {
        Optional<Student> studentByEmailId = studentRepo.getStudentByEmailId(email);
        if (studentByEmailId.isPresent()){
            return helper.getStudentDto(studentByEmailId.get());
        }
        else {
            return null;
        }
    }

    @Override
    public Object[] getStudentAddressAndFirstnameByEmailID(String email) {
        return studentRepo.getAddressAndFirstnameByEmailID(email);
    }

    @Override
    public FirstNameLastNameAddress getStudentFirstNameLastNameAddressByID(Long id) {
        return studentRepo.getFirstNameLastNameAddressByID(id);
    }

    @Override
    public void updateStudentById(Long id,StudentDto studentDto) {
        studentRepo.updateById(id,studentDto.getFirstName(),studentDto.getLastName(),studentDto.getEmailId(),studentDto.getAddress());
    }

    @Override
    public void insertStudentData(StudentDto studentDto) {
        studentRepo.insertData(studentDto.getFirstName(),studentDto.getLastName(),studentDto.getEmailId(),studentDto.getAddress());
    }

    @Override
    public void deleteStudentByID(Long id) {
        studentRepo.deleteById(id);
    }
}
