package peaksoft.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.StudentRequest;
import peaksoft.dto.StudentResponse;
import peaksoft.dto.StudentResponseView;
import peaksoft.entity.Student;
import peaksoft.service.StudentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/student")
public class StudentController {
    private final StudentService service;

    @PostMapping
    public StudentResponse addStudent(@RequestBody StudentRequest student){
        return service.addStudent(student);
    }

    @GetMapping("{id}")
    public StudentResponse  getById(@PathVariable Long id){
        return service.getById(id);
    }

    @DeleteMapping("{id}")
    public StudentResponse deleteById(@PathVariable Long id){
        return service.deleteById(id);
    }

    @PatchMapping("{id}")
    public StudentResponse update(@PathVariable Long id, @RequestBody StudentRequest request){
        return service.updateStudent(id, request);
    }

    @GetMapping("/all")
    public List<Student> getAll(){
        return service.findAllStudents();
    }

    @GetMapping
    public StudentResponseView getAllPagination(@RequestParam(name = "text", required = false) String text,
                                                @RequestParam int page,
                                                @RequestParam int size){
        return service.getAllStudentPagination(text, page, size);
    }
}
