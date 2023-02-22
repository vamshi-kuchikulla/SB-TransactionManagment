package org.cst.TransactionManagment.service;

import lombok.RequiredArgsConstructor;
import org.cst.TransactionManagment.dto.StudentDto;
import org.cst.TransactionManagment.entity.Student;
import org.cst.TransactionManagment.repository.StudentRepository;
import org.cst.TransactionManagment.util.ValueMapper;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final EmailService emailService;

    @Transactional
    public StudentDto addStudent(StudentDto studentDto) throws Exception {
        Student student = ValueMapper.studentDtoToEntity(studentDto);
        Student savedStudent = studentRepository.save(student);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo("********");
        mailMessage.setFrom("********");
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setText("Registration is successful!! A verification link has been sent to your registered email to activate the account. ");
        emailService.sendEmail(mailMessage);

        return ValueMapper.studentEntityToDto(savedStudent);
    }

    public List<StudentDto> getStudents() {
        List<Student> students = studentRepository.findAll();
        List<StudentDto> studentDtos = new ArrayList<>();
        for (Student s : students) {
            StudentDto dto = ValueMapper.studentEntityToDto(s);
            studentDtos.add(dto);
        }
        return studentDtos;
    }

    public StudentDto getStudentById(int rollNo) {
        Student studentById = studentRepository.findAllByRollNo(rollNo);
        return ValueMapper.studentEntityToDto(studentById);
    }

    public StudentDto updateStudent(StudentDto studentDto, int rollNo) {
        Student exsistingStudent = studentRepository.findAllByRollNo(rollNo);
        if (exsistingStudent != null) {
            exsistingStudent.setRollNo(studentDto.getRollNo());
            exsistingStudent.setEmail(studentDto.getEmail());
            exsistingStudent.setFullName(studentDto.getFullName());
            exsistingStudent.setMobileNumber(studentDto.getMobileNumber());
            exsistingStudent.setAddress(ValueMapper.getAddressEntity(studentDto.getAddress()));
        }else {
            throw new NoSuchElementException("There is no Record for a given rollNumber");
        }
        Student updatedStudent = studentRepository.save(exsistingStudent);
        return ValueMapper.studentEntityToDto(updatedStudent);
    }

    public String deleteStudentById(int rollNo) {
        String message;
        if(studentRepository.deleteByRollNo(rollNo)){
          message = "Student deleted successfully!!!";
      }else {
          message = "Student deleted successfully!!!";
     }
     return message;
    }
}