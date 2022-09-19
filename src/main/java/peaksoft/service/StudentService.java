package peaksoft.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import peaksoft.dto.StudentRequest;
import peaksoft.dto.StudentResponse;
import peaksoft.dto.StudentResponseView;
import peaksoft.entity.Student;
import peaksoft.repository.StudentRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository repository;

    public StudentResponse addStudent(StudentRequest request){
        Student student = createStudent(request);
        repository.save(student);
        return getResponse(student);
    }

    public StudentResponse updateStudent(Long id, StudentRequest request){
        Student student = repository.findById(id).get();
        Student student1 = updateStudent(student, request);
        repository.save(student1);
        return getResponse(student1);
    }

    public StudentResponse getById(Long id){
        Student student = repository.findById(id).get();
        return getResponse(student);
    }

    public StudentResponse deleteById(Long id){
        Student student = repository.findById(id).get();
        repository.delete(student);
        return getResponse(student);
    }

    public List<Student> findAllStudents(){
        return repository.findAll();
    }

    public StudentResponseView getAllStudentPagination(String text, int page, int size){
        StudentResponseView responseView = new StudentResponseView();
        Pageable pageable = PageRequest.of(page -1, size);
        responseView.setResponse(getStudents(search(text, pageable)));
        return responseView;
    }

    public List<StudentResponse> getStudents(List<Student> students){
        List<StudentResponse> responses = new ArrayList<>();
        for (Student student : students) {
            responses.add(getResponse(student));
        }
        return responses;
    }

    private List<Student> search(String name, Pageable pageable){
        String text = name == null ? "" : name;
        return repository.searchByName(text.toUpperCase(), pageable);
    }

    public Student createStudent(StudentRequest request){
        Student student = new Student();
        student.setName(request.getName());
        student.setSurname(request.getSurname());
        student.setAge(request.getAge());
        student.setEmail(request.getEmail());
        student.setDeleted(student.isDeleted());
        student.setActive(student.isActive());
        student.setCreated(LocalDateTime.now());
        return student;
    }

    public Student updateStudent(Student student ,StudentRequest request){
        student.setName(request.getName());
        student.setSurname(request.getSurname());
        student.setAge(request.getAge());
        student.setEmail(request.getEmail());
        student.setDeleted(student.isDeleted());
        student.setActive(student.isActive());
        student.setCreated(LocalDateTime.now());
        return student;
    }

    public StudentResponse getResponse(Student student){
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setId(student.getId());
        studentResponse.setActive(student.isActive());
        studentResponse.setSurname(student.getSurname());
        studentResponse.setAge(student.getAge());
        studentResponse.setName(student.getName());
        studentResponse.setEmail(student.getEmail());
        studentResponse.setDeleted(student.isDeleted());
        return studentResponse;
    }
}
