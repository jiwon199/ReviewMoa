package com.ssafy.ReviewMoa_Spring.dto;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//참고: https://wonit.tistory.com/260

@Entity
@Data
public class testUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;
    /*

    create table testUser(
        id long primary key not null auto increment,
        username varchar(50),
        password varchar(50)
    )
     */
}
