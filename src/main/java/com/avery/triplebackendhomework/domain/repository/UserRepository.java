package com.avery.triplebackendhomework.domain.repository;

import com.avery.triplebackendhomework.domain.vo.UserVO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserVO, String> {
}
