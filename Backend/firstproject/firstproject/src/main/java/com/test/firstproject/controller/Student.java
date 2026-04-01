package com.test.firstproject.controller;

public class Student {
    private String name;
    private long phoneNumber;
    private int age;
    private String address;
    private int pincode;
    private String status;

    public String getName() {
        return name;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public int getPincode() {
        return pincode;
    }

    public String getStatus() {
        return status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", pincode=" + pincode +
                ", status='" + status + '\'' +
                '}';
    }

    public Student() {
    }

    public Student(String name, long phoneNumber, int age, String address, int pincode, String status) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.address = address;
        this.pincode = pincode;
        this.status = status;
    }
}
