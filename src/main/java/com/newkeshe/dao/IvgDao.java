package com.newkeshe.dao;

import com.newkeshe.entity.Ivg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface IvgDao extends JpaRepository<Ivg,Integer> {
    Optional<Ivg> findById (Integer ivgId);

    @Override
    <S extends Ivg> S save(S s);
    @Transactional
    @Modifying
    void deleteById(Integer ivgId);

}
