package com.ssafy.ReviewMoa_Spring.controller.user;

import com.ssafy.ReviewMoa_Spring.dto.user.User;
import com.ssafy.ReviewMoa_Spring.repository.user.userRepository;
import com.ssafy.ReviewMoa_Spring.service.user.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//일단 userController라고 해놨는데 자유롭게 지우거나 바꿔주세요.
@RestController
@RequestMapping("/user")
@CrossOrigin(origins="*", allowedHeaders = "*")
public class userController {
    @Autowired
    private userService service;


    //회원 등록하는 함수
    @PostMapping("/regist")
    public void regist(@RequestBody User user){
        System.out.println("회원등록#########################");
        System.out.println(user);
        service.registPost(user);
        System.out.println("ok");
//        return repository.save(user);
    }
}
