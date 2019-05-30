package com.newkeshe.dao;

import com.newkeshe.entity.Ivg;
import com.newkeshe.entity.User;
import com.newkeshe.entity.User_Ivg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserIvgDao extends JpaRepository<User_Ivg,Integer> {
    @Override
    <S extends User_Ivg> S save(S s);

    @Override
    List<User_Ivg> findAll();

    List<User_Ivg> findByUserAndIvg(User user,Ivg ivg);
    @Transactional
    @Modifying
    void deleteByUserAndIvg(User user, Ivg ivg);

    @Transactional
    @Modifying
    void deleteById(Integer id);

    List<User_Ivg> findByIvg(Ivg ivg);
    List<User_Ivg> findByUser(User user);

    @Query(value = "select count(u.ivg) from User_Ivg u where u.ivg.id = ?1")
    Integer findCountIvgByIvgId(Integer ivgId);

}
