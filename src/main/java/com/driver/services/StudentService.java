package com.driver.services;


import com.driver.models.Card;
import com.driver.models.Student;
import com.driver.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {


    @Autowired
    CardService cardService;

    @Autowired
    StudentRepository studentRepository;

    public Student getDetailsByEmail(String email){
        return studentRepository.findByEmailId(email);
    }

    public Student getDetailsById(int id){
        return studentRepository.findById(id).get();
    }

    public void createStudent(Student student){
        Card newCard = cardService.createAndReturn(student);
    }

    public void updateStudent(Student student){
        studentRepository.updateStudentDetails(student);

    }

    public void deleteStudent(int id){
        //Delete student and deactivate corresponding card
        cardService.deactivateCard(id);
        studentRepository.deleteCustom(id);
    }
}
