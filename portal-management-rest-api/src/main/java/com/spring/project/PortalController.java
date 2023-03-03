package com.spring.project;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.spring.project.dto.Book;
import com.spring.project.dto.Course;
import com.spring.project.dto.Laptop;
import com.spring.project.dto.Student;
import com.spring.project.repository.BookRepository;
import com.spring.project.repository.CourseRepository;
import com.spring.project.repository.LaptopRepository;
import com.spring.project.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/portal-management")
public class PortalController {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private LaptopRepository laptopRepository;

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private BookRepository bookRepository;

	// CRUD operations for Student model

	@GetMapping("/students")
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	@GetMapping("/students/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable(value = "id") String studentId)
			throws ResourceNotFoundException {
		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException("Student not found for this id :: " + studentId));
		return ResponseEntity.ok().body(student);
	}

	@PostMapping("/students")
	public Student createStudent(@Valid @RequestBody Student student) {
		return studentRepository.save(student);
	}

	@PutMapping("/students/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable(value = "id") String studentId,
			@Valid @RequestBody Student studentDetails) throws ResourceNotFoundException {
		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException("Student not found for this id :: " + studentId));

		student.setName(studentDetails.getName());
		student.setAge(studentDetails.getAge());
		student.setPhoneNumber(studentDetails.getPhoneNumber());
		student.setBranch(studentDetails.getBranch());
		student.setDepartment(studentDetails.getDepartment());
		student.setAddress(studentDetails.getAddress());

		final Student updatedStudent = studentRepository.save(student);
		return ResponseEntity.ok(updatedStudent);
	}

	@DeleteMapping("/students/{id}")
	public Map<String, Boolean> deleteStudent(@PathVariable(value = "id") String studentId)
			throws ResourceNotFoundException {
		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException("Student not found for this id :: " + studentId));

		studentRepository.delete(student);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	// CRUD operations for Laptop model

	@GetMapping("/laptops")
	public List<Laptop> getAllLaptops() {
		return laptopRepository.findAll();
	}

	@GetMapping("/laptops/{id}")
	public ResponseEntity<Laptop> getLaptopById(@PathVariable(value = "id") String laptopId)
			throws ResourceNotFoundException {
		Laptop laptop = laptopRepository.findById(laptopId)
				.orElseThrow(() -> new ResourceNotFoundException("Laptop not found for this id :: " + laptopId));
		return ResponseEntity.ok().body(laptop);
	}

	@PostMapping("/laptops")
	public Laptop createLaptop(@Valid @RequestBody Laptop laptop) {
		return laptopRepository.save(laptop);
	}

	@PutMapping("/laptops/{id}")
	public ResponseEntity<Laptop> updateLaptop(@PathVariable(value = "id") String laptopId,
			@Valid @RequestBody Laptop laptopDetails) throws ResourceNotFoundException {
		Laptop laptop = laptopRepository.findById(laptopId)
				.orElseThrow(() -> new ResourceNotFoundException("Laptop not found for this id :: " + laptopId));

		laptop.setName(laptopDetails.getName());
		laptop.setBrand(laptopDetails.getBrand());
		laptop.setPrice(laptopDetails.getPrice());
		laptop.setStudent(laptopDetails.getStudent());

		final Laptop updatedLaptop = laptopRepository.save(laptop);
		return ResponseEntity.ok(updatedLaptop);
	}

	@DeleteMapping("/laptops/{id}")
	public ResponseEntity<?> deleteLaptop(@PathVariable(value = "id") String laptopId)
			throws ResourceNotFoundException {
		laptopRepository.deleteById(laptopId);
		return ResponseEntity.ok().build();
	}

	// CRUD operations for Course model

	@GetMapping("/courses")
	public List<Course> getAllCourses() {
		return courseRepository.findAll();
	}

	@GetMapping("/courses/{id}")
	public ResponseEntity<Course> getCourseById(@PathVariable(value = "id") String courseId)
			throws ResourceNotFoundException {
		Course course = courseRepository.findById(courseId)
				.orElseThrow(() -> new ResourceNotFoundException("Course not found for this id :: " + courseId));
		return ResponseEntity.ok().body(course);
	}

	@PostMapping("/courses")
	public Course createCourse(@Valid @RequestBody Course course) {
		return courseRepository.save(course);
	}

	@PutMapping("/courses/{id}")
	public ResponseEntity<Course> updateCourse(@PathVariable(value = "id") String courseId,
			@Valid @RequestBody Course courseDetails) throws ResourceNotFoundException {
		Course course = courseRepository.findById(courseId)
				.orElseThrow(() -> new ResourceNotFoundException("Laptop not found for this id :: " + courseId));

		course.setDescription(courseDetails.getDescription());
		course.setDuration(courseDetails.getDuration());
		course.setTitle(courseDetails.getTitle());
		course.setStudentList(courseDetails.getStudentList());

		final Course updatedCourse = courseRepository.save(course);
		return ResponseEntity.ok(updatedCourse);
	}

	@DeleteMapping("/courses/{id}")
	public ResponseEntity<?> deleteCourse(@PathVariable(value = "id") String courseId)
			throws ResourceNotFoundException {
		bookRepository.deleteById(courseId);
		return ResponseEntity.ok().build();
	}

	// CRUD operations for Book model

	@GetMapping("/books")
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	@GetMapping("/books/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable(value = "id") String bookId)
			throws ResourceNotFoundException {
		Book book = bookRepository.findById(bookId)
				.orElseThrow(() -> new ResourceNotFoundException("Book not found for this id :: " + bookId));
		return ResponseEntity.ok().body(book);
	}

	@PostMapping("/books")
	public Book createBook(@Valid @RequestBody Book book) {
		return bookRepository.save(book);
	}

	@PutMapping("/books/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable(value = "id") String bookId,
			@Valid @RequestBody Book bookDetails) throws ResourceNotFoundException {
		Book book = bookRepository.findById(bookId)
				.orElseThrow(() -> new ResourceNotFoundException("Book not found for this id :: " + bookId));

		book.setAuthor(bookDetails.getAuthor());
		book.setDescription(bookDetails.getDescription());
		book.setPrice(bookDetails.getPrice());
		book.setTitle(bookDetails.getTitle());
		book.setStudent(bookDetails.getStudent());

		final Book updatedbook = bookRepository.save(book);
		return ResponseEntity.ok(updatedbook);
	}

	@DeleteMapping("/books/{id}")
	public ResponseEntity<?> deleteBook(@PathVariable(value = "id") String bookId) throws ResourceNotFoundException {
		bookRepository.deleteById(bookId);
		return ResponseEntity.ok().build();
	}

}
