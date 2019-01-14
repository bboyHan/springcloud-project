package com.bboyhan.cloud.repository;

import com.bboyhan.cloud.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Auther: wushaohan
 * @Date: 2019/1/14 10:56
 * @Description:
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {

}
