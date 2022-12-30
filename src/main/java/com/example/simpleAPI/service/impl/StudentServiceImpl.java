package com.example.simpleAPI.service.impl;
import com.example.simpleAPI.dto.StudentCreateDTO;
import com.example.simpleAPI.dto.StudentViewDTO;
import com.example.simpleAPI.exceptions.NotFoundException;
import com.example.simpleAPI.model.Student;
import com.example.simpleAPI.repository.StudentRepository;
import com.example.simpleAPI.service.StudentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public StudentViewDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found exception"));
        return StudentViewDTO.of(student);
    }

    @Override
    public List<StudentViewDTO> getStudents() {
        return studentRepository.findAll().stream().map(StudentViewDTO::of).collect(Collectors.toList());
    }

    @Override
    public StudentViewDTO addNewStudent(StudentCreateDTO studentCreateDTO) {
        Student s = new Student(studentCreateDTO.getName(), studentCreateDTO.getEmail(), studentCreateDTO.getDob());

        final Student student = studentRepository
                .save(s);
        return StudentViewDTO.of(student);
    }


    @Override
    public void deleteStudent(Long id) {
        boolean exists = studentRepository.existsById(id);

        if (!exists) {
            throw new NotFoundException("Not Found Exception");
        }
        studentRepository.deleteById(id);
    }


    @Transactional
    @Override
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new NotFoundException("Not found exception"));
        if (name != null && name.length() > 0 && !Objects.equals(name, student.getName())) {
            student.setName(name);
        }
        if (email != null && email.length() > 0 && !Objects.equals(email, student.getEmail())) {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("email taken");

            }
            student.setEmail(email);
        }
        studentRepository.save(student);
    }

}

