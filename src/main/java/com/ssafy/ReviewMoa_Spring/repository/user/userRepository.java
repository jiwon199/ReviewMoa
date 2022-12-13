package com.ssafy.ReviewMoa_Spring.repository.user;

import com.ssafy.ReviewMoa_Spring.dto.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userRepository extends JpaRepository<User, Long> {

}
