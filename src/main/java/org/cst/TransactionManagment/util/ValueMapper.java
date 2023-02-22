package org.cst.TransactionManagment.util;

import org.cst.TransactionManagment.dto.AddressDto;
import org.cst.TransactionManagment.dto.StudentDto;
import org.cst.TransactionManagment.entity.Address;
import org.cst.TransactionManagment.entity.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
public class ValueMapper {
    public static Student studentDtoToEntity(StudentDto studentDto) {
        Student student = new Student();
        student.setRollNo(studentDto.getRollNo());
        student.setFullName(studentDto.getFullName());
        student.setEmail(studentDto.getEmail());
        student.setMobileNumber(studentDto.getMobileNumber());
        student.setAddress(addressDtoToEntity(studentDto.getAddress()));
        return student;
    }

    public static StudentDto studentEntityToDto(Student student) {
        StudentDto studentDto = new StudentDto();
        studentDto.setRollNo(student.getRollNo());
        studentDto.setFullName(student.getFullName());
        studentDto.setEmail(student.getEmail());
        studentDto.setMobileNumber(student.getMobileNumber());
        studentDto.setAddress(addressEntityToDto(student.getAddress()));
        return studentDto;

    }

    public static List<Address> getAddressEntity(List<AddressDto> address) {
        return address.stream().map(dto -> Address.builder()
                .doorNumber(dto.getDoorNumber())
                .street(dto.getStreet())
                .state(dto.getState())
                .city(dto.getCity())
                .pinCode(dto.getPinCode()).build()).collect(Collectors.toList());
    }

    public static List<AddressDto> addressEntityToDto(List<Address> address) {
        List<AddressDto> addressDtoList = new ArrayList<>();
        for(Address a :address ){
            AddressDto addressDtoItem = new AddressDto();
            addressDtoItem.setDoorNumber(a.getDoorNumber());
            addressDtoItem.setStreet(a.getStreet());
            addressDtoItem.setCity(a.getCity());
            addressDtoItem.setState(a.getState());
            addressDtoItem.setPinCode(a.getPinCode());

            addressDtoList.add(addressDtoItem);
        }
        return addressDtoList;
    }

    public static List<Address> addressDtoToEntity(List<AddressDto> addressDto) {
        List<Address> address = new ArrayList<>();
        for(AddressDto a :addressDto ){
            Address addressItem = new Address();
            addressItem.setDoorNumber(a.getDoorNumber());
            addressItem.setStreet(a.getStreet());
            addressItem.setCity(a.getCity());
            addressItem.setState(a.getState());
            addressItem.setPinCode(a.getPinCode());
            address.add(addressItem);
        }
        return address;
    }
}
