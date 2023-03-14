package com.springrest.springrest.controller;

import java.util.List;

import com.springrest.springrest.model.Student;
import com.springrest.springrest.service.StudentService;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @PostMapping("/addStudent")
    public ResponseEntity<String> addStudent(@RequestBody String student){
        JSONObject jsonObject = new JSONObject(student);
        JSONObject errorList = studentService.addStudent(jsonObject);
        if(errorList.has("errorMessage")){
            return new ResponseEntity<>(errorList.toString(), HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>("Student saved", HttpStatus.CREATED);
        }
    }
    @GetMapping("/getByFirstName")
    public ResponseEntity<String> getStudent(@RequestParam("name") String name){
        List<Student> studentByName = studentService.getStudentByName(name);
        if(studentByName.isEmpty()){
            return new ResponseEntity<>("No student with firstName like - "+name, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(studentByName.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getByLastName")
    public  ResponseEntity<String> getStudentByLastName(@RequestParam("name") String lastName){
        List<Student> studentByLastName = studentService.getStudentByLastName(lastName);
        if(studentByLastName.isEmpty()){
            return new ResponseEntity<>("No student with lasstName like - "+lastName, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(studentByLastName.toString(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/getByAge")
    public    ResponseEntity<String> getStudentByAge(@RequestParam("age") Integer age){
        List<Student> studentsByAge = studentService.getStudentsByAgeFirstNameSorted(age);
        if(studentsByAge.isEmpty()){
            return new ResponseEntity<>("No student with age like - "+age, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(studentsByAge.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<String> deleteById(@RequestParam("id") Integer id){
        if(studentService.deleteStudentById(id)){
            return new ResponseEntity<>("Student deleted with id - "+id,HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No student is present with id - "+id,HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateStudentDetails")
    public ResponseEntity<String> updateStudent(@RequestBody String student){
        JSONObject jsonObject = new JSONObject(student);
        JSONObject errorList = studentService.updateStudent(jsonObject);
        if(errorList.has("errorMessage")){
            return new ResponseEntity<>(errorList.toString(), HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>("Student updated", HttpStatus.CREATED);
        }
    }
    
    @GetMapping("/getAllStudents")
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }


}
