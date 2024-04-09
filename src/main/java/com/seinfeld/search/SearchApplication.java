package com.seinfeld.search;

import com.github.javafaker.Faker;
import com.seinfeld.search.models.Address;
import com.seinfeld.search.models.Student;
import com.seinfeld.search.models.Subject;
import com.seinfeld.search.repositories.AddressRepository;
import com.seinfeld.search.repositories.StudentRepository;
import com.seinfeld.search.repositories.SubjectRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(SearchApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(
            AddressRepository addressRepository,
            StudentRepository studentRepository,
            SubjectRepository subjectRepository
    ) {
        return args -> {
            Faker faker = new Faker();

            for (int i = 0; i < 20; i++) {
                Address address = new Address();
                address.setCity(faker.address().cityName());
                List<Subject> subjects = new ArrayList<>();
                Student student = new Student();
                student.setName(faker.name().firstName());
                student.setAddress(address);
                for (int j = 0; j < 3; j++) {
                    Subject subject = new Subject();
                    subject.setName(faker.name().lastName());
                    subject.setStudentId(student);
                    subjects.add(subject);
                }
                student.setSubjects(subjects);
                addressRepository.save(address);
                studentRepository.save(student);
            }
        };
    }

}
