package com.driver.controller;

import com.driver.models.Student;
import com.driver.security.AuthorityConstants;
import com.driver.security.User;
import com.driver.security.UserRepository;
import com.driver.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

//Add required annotations
@RestController
@RequestMapping("/student")
public class StudentController {
      @Autowired
    StudentService studentService;
    //Add required annotations
    @GetMapping("/studentByEmail")
    public ResponseEntity getStudentByEmail(@RequestParam("email") String email){
        Student obj = studentService.getDetailsByEmail(email);
        return new ResponseEntity<>("Student details printed successfully "+obj, HttpStatus.OK);
    }

    //Add required annotations\
    @GetMapping("/studentById")
    public ResponseEntity getStudentById(@RequestParam("id") int id){
        Student obj = studentService.getDetailsById(id);
        return new ResponseEntity<>("Student details printed successfully "+obj, HttpStatus.OK);
    }

    //Add required annotations
    @PostMapping("/")
    public ResponseEntity createStudent(@RequestBody Student student){
        studentService.createStudent(student);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        com.example.library.studentlibrary.security.User user =  com.example.library.studentlibrary.security.User.builder()
                .username(student.getEmailId())
                .password(encoder.encode("pass1234"))
                .authority(com.example.library.studentlibrary.security.AuthorityConstants.STUDENT_AUTHORITY)
                .build();
        userRepository.save(user);
        return new ResponseEntity<>("the student is successfully added to the system", HttpStatus.CREATED);
    }

    //Add required annotations
    @PutMapping("/")
    public ResponseEntity updateStudent(@RequestBody Student student){

        return new ResponseEntity<>("student is updated", HttpStatus.ACCEPTED);
    }

    //Add required annotations
    @DeleteMapping("/")
    public ResponseEntity deleteStudent(@RequestParam("id") int id){

        return new ResponseEntity<>("student is deleted", HttpStatus.ACCEPTED);
    }

}
