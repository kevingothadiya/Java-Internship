package com.example.onttomanyfullproject.dto;
import com.example.onttomanyfullproject.ValidPassWord;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class StudentDto {
    private Long id;

    @NotBlank(message = "user Name is Required")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    private String userName;

    @NotBlank(message = "PassWord is Required")
    @ValidPassWord
    private String passWord;

    @NotBlank(message = "Branch is Required")
    private String branch;

    @NotBlank(message = "Email ID is Required")
    @Email(message = "Invalid email format")
    private String emailId;

    @NotBlank(message = "Mobile Number is Required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be 10 digits")
    private String mobileNo;

    @NotBlank(message = "Role is Required")
    private String role;

    @Valid
    private List<AddressDto> addresses;
}
