package com.avery.triplebackendhomework.domain.repository;

import com.avery.triplebackendhomework.domain.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class RepositoryTests {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveUserTest(){
        UserVO userVO = UserVO.builder().userId("test1234").build();
        log.info("test"+userVO.toString());
        userRepository.save(userVO);
    }
}
