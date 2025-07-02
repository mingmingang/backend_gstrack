package com.astratech.backend_gstrack.Repository;

import com.astratech.backend_gstrack.VO.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository("UserRepository")
public interface UserRepository extends JpaRepository<User, Serializable> {

    User findByUserId(String userId);

    List<User> findAllByOrderByUserIdAsc();
}
