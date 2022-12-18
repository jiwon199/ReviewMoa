package com.ssafy.ReviewMoa_Spring.service.user;

import com.ssafy.ReviewMoa_Spring.dto.user.User;
import com.ssafy.ReviewMoa_Spring.repository.user.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class userService {
    @Autowired
    private userRepository userRepo;

    public void registPost(User user) {
        System.out.println("registPost");
        userRepo.save(user);
    }
}
