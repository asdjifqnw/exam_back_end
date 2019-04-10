package com.newkeshe.dao;

import com.newkeshe.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User,Integer> {
    List<User> findAll();
    <S extends User>S save(S s);
    List<User> findByUPhone(String uPhone);
    List<User> findByUId(Integer uId);
}
