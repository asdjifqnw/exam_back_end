package com.newkeshe.dao;

import com.newkeshe.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TaskDao extends JpaRepository<Task,Integer> {
    @Override
    <S extends Task> S save(S s);
    @Transactional
    @Modifying
    void deleteByTId(Integer tId);

    List<Task> findByTId(Integer tId);
}
