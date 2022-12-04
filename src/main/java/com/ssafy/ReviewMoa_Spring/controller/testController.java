package com.ssafy.ReviewMoa_Spring.controller;


import com.ssafy.ReviewMoa_Spring.dto.testUser;
import com.ssafy.ReviewMoa_Spring.repository.testRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/test")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class testController {

    @Autowired
    private testRepository repository;

    /* http://localhost:9999/moa/test/hello */
    @GetMapping("hello")
    public String Hello(){
        System.out.println("hello 컨트롤러 실행됨");
        return "hello";
    }

    @PostMapping("/user")
    public testUser create(@RequestBody testUser testUser) {
        System.out.println("create method");
        return repository.save(testUser);
    }

    @GetMapping("/user/{id}")
    public String read(@PathVariable Long id) {

        Optional<testUser> userOptional = repository.findById(id);
        userOptional.ifPresent(System.out::println);

        return "successfully executed"+ userOptional.toString()+" 입니다.";
    }

}
