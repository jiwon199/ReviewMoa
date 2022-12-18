package com.ssafy.ReviewMoa_Spring.dto.user;

import lombok.*;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="userId")
    private Long userId; //왜 Long으로 해야하는가?
    private String realId;
    private String userPwd;
    private String userName;
    private int userBirth;
    private String userGender;


}
