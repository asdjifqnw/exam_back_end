package com.newkeshe.dao;

import com.newkeshe.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User,Integer> {
    List<User> findAll();
    <S extends User>S save(S s);
    User findByPhone(String uPhone);
    Optional<User> findById(Integer uId);
    @Transactional
    @Modifying
    void deleteById(Integer uId);
}
