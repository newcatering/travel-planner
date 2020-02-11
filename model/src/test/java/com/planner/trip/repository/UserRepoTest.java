package com.planner.trip.repository;


import com.planner.trip.code.Auth;
import com.planner.trip.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepoTest {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void 저장하고_불러오기() {
        //given
        userRepo.save(User.builder()
                .email("test@test.com")
                .nickname("test")
                .phone("01012345678")
                .password(passwordEncoder.encode("1234"))
                .auth(Auth.USER).build());

        //when
        List<User> userList = userRepo.findAll();

        //then
        User user = userList.get(0);
        assertThat(user.getEmail(), is("test@test.com"));
        assertThat(user.getAuth(), is(Auth.USER));
    }
}