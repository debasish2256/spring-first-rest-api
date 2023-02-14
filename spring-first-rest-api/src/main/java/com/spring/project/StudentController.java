package com.spring.project;

import java.util.List;

import com.spring.project.dto.Student;
import com.spring.project.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private StudentRepository studentRepository;

	@PostMapping
	public Student addStudent(@RequestBody Student student) {
		return studentRepository.save(student);
	}

	@PutMapping("/{id}")
	public Student updateDepartment(@PathVariable int id, @RequestBody Student student) {
		Student existingStudent = studentRepository.findById(id);
		existingStudent.setDepartment(student.getDepartment());
		return studentRepository.save(existingStudent);
	}

	@DeleteMapping("/{id}")
	public void deleteStudent(@PathVariable int id) {
		studentRepository.deleteById(id);
	}

	@GetMapping
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	@GetMapping("/{id}")
	public Student getStudentById(@PathVariable int id) {
		return studentRepository.findById(id);
	}
}
