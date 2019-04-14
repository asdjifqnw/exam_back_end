package com.newkeshe.dao;

import com.newkeshe.entity.Ivg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface IvgDao extends JpaRepository<Ivg,Integer> {
    List<Ivg> findByIvgId (Integer ivgId);

    @Override
    <S extends Ivg> S save(S s);
    @Transactional
    @Modifying
    void deleteByIvgId(Integer ivgId);

}
