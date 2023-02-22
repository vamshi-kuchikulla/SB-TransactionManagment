package org.cst.TransactionManagment.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.cst.TransactionManagment.dto.StudentDto;
import org.cst.TransactionManagment.exception.CustomError;
import org.cst.TransactionManagment.service.StudentService;
import org.cst.TransactionManagment.util.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@EnableTransactionManagement
public class TransactionController {
    private static final String SUCCESS = "success";
    private static final String ERROR = "ERROR";
    private final StudentService studentService;

    @GetMapping
    public String getMessage() {
        return "Transaction Controller";
    }

    @PostMapping(value = "/addStudent")
    public ResponseEntity<APIResponse> addStudent(@RequestBody @Valid StudentDto studentDto) throws Exception {
        StudentDto studentResponse = studentService.addStudent(studentDto);
        APIResponse<StudentDto> response = APIResponse.<StudentDto>builder()
                .status(SUCCESS)
                .results(studentResponse)
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(value = "/getStudents")
    public ResponseEntity<APIResponse> getStudents() {
        List<StudentDto> sList = studentService.getStudents();
        APIResponse<List<StudentDto>> response = APIResponse.<List<StudentDto>>builder()
                .status(SUCCESS)
                .results(sList)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/getStudentById/{id}")
    public ResponseEntity<APIResponse> getStudentById(@PathVariable(value ="id") int rollNo) {
        StudentDto studentById = studentService.getStudentById(rollNo);
        if(studentById != null) {
            APIResponse<StudentDto> response = APIResponse.<StudentDto>builder()
                    .status(SUCCESS)
                    .results(studentById)
                    .build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else {
            CustomError error = new CustomError();
            error.setDetails("rollNumber not Valid");
            error.setMessage("Please Provide Valid RollNumber");
            error.setTimeStamp(LocalDateTime.now());
            APIResponse<CustomError> response = APIResponse.<CustomError>builder()
                    .status(ERROR)
                    .errors(error)
                    .build();
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/updateStudentById/{id}")
    public ResponseEntity<APIResponse> updateStudentById(@RequestBody @Valid StudentDto studentDto,
                                                         @PathVariable(value ="id") int rollNo) {
        StudentDto updateStudent = studentService.updateStudent(studentDto, rollNo);
        APIResponse<StudentDto> response = APIResponse.<StudentDto>builder()
                .status(SUCCESS)
                .results(updateStudent)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteStudentById/{id}")
    public ResponseEntity<APIResponse> deleteStudentById(@PathVariable(value ="id") int rollNo) {
       String message = studentService.deleteStudentById(rollNo);
        APIResponse<String> response = APIResponse.<String>builder()
                .status(SUCCESS)
                .results(message)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
