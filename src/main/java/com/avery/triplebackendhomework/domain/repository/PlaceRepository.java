package com.avery.triplebackendhomework.domain.repository;

import com.avery.triplebackendhomework.domain.vo.PlaceVO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<PlaceVO,String> {
}
