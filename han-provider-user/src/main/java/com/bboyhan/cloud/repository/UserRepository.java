package com.bboyhan.cloud.repository;

import com.bboyhan.cloud.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Auther: bboyHan
 * @Date: 2019/1/20 13:41
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {

}
