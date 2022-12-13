package com.ssafy.ReviewMoa_Spring.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private String userId;
    private String userPwd;
    private String userName;
    private int userBirth;
    private String userGender;

}
