package org.cst.TransactionManagment;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.cst.TransactionManagment.controller.AbstractContainerBaseTest;
import org.cst.TransactionManagment.dto.AddressDto;
import org.cst.TransactionManagment.dto.StudentDto;
import org.cst.TransactionManagment.entity.Address;
import org.cst.TransactionManagment.entity.Student;
import org.cst.TransactionManagment.repository.StudentRepository;
import org.cst.TransactionManagment.service.StudentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Testcontainers
class TransactionManagementApplicationTest  {
    @Container
    private static MySQLContainer MY_SQL_CONTAINER = new MySQLContainer("mysql:latest");
    @Autowired
    MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Mock
    private StudentRepository studentRepository;

    @Test
    void addStudentTest() throws Exception {
        System.out.println(MY_SQL_CONTAINER.getDatabaseName());
        System.out.println(MY_SQL_CONTAINER.getDriverClassName());
        System.out.println(MY_SQL_CONTAINER.getJdbcUrl());
        System.out.println(MY_SQL_CONTAINER.getPassword());

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/api/addStudent")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(getStudentDto())));

        response.andDo(print())
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    public StudentDto getStudentDto() {
        List<AddressDto> addressDto = Arrays.asList(
                new AddressDto("1A", "Main Street", "Anytown", "Anystate", 12345),
                new AddressDto("2A", "Side Street", "Mytown", "Mystate", 54321));
        StudentDto studentDto = new StudentDto();
        studentDto.setRollNo(101);
        studentDto.setFullName("John Doe");
        studentDto.setAddress(addressDto);
        studentDto.setMobileNumber(1234567890);
        studentDto.setEmail("johndoe@example.com");
        return studentDto;
    }

    public Student getStudent() {
        List<Address> address = Arrays.asList(
                new Address("122", "Main Street", "Anytown", "Anystate", 12345),
                new Address("211", "Side Street", "Mytown", "Mystate", 54321));
        Student student = new Student();
        student.setRollNo(101);
        student.setFullName("John Doe1");
        student.setAddress(address);
        student.setMobileNumber(1234567899);
        student.setEmail("johndoe@example.com");
        return student;
    }
}