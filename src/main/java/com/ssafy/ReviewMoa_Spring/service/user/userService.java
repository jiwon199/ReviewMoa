package com.ssafy.ReviewMoa_Spring.service.user;

import com.ssafy.ReviewMoa_Spring.dto.user.User;
import com.ssafy.ReviewMoa_Spring.repository.user.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class userService {
    @Autowired
    private userRepository userRepo;

    public void registPost(User user) {
        System.out.println("registPost");
        List<User> lists=userRepo.findAll();
        int len=lists.size();
        for(int i=0;i<len;i++){
            if(lists.get(i).getRealId().equals(user.getRealId())){
                System.out.println("same id is already registered!!");
                break;
            }
        }
        userRepo.save(user);
        System.out.println("complete regist");
    }

    public User loginPost(User user) {
        System.out.println("loginPost");
        List<User> lists=userRepo.findAll();
        int len=lists.size();
        User result = null;
//        System.out.println(user.getRealId().length());
//        System.out.println(user.getUserPwd());
//        if(user.getRealId().equals(lists.get(6).getRealId())){
//            System.out.println("why??");
//        }
//        System.out.println(lists.get(6).getRealId().length());
//        System.out.println(lists.get(6).getUserPwd());
        for(int i=0;i<len;i++) {
            if (lists.get(i).getRealId().equals(user.getRealId()) && lists.get(i).getUserPwd().equals(user.getUserPwd()) ) {
                result=lists.get(i);
                System.out.println(i);
            }
        }
        return result;
    }

    public void userDelete(String userId) {
        List<User> lists=userRepo.findAll();
        int len=lists.size();
        System.out.println(userId);
        for(int i=0;i<len;i++){
            if(lists.get(i).getRealId().equals(userId)){
                Long num=lists.get(i).getUserId();
                System.out.println("성공");
                userRepo.deleteById(num);
                break;
            }
        }
//        userRepo.save(user);
//        System.out.println("complete regist");
    }
}
