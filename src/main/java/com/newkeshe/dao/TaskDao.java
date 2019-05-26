package com.newkeshe.dao;

import com.newkeshe.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface TaskDao extends JpaRepository<Task,Integer> {
    @Override
    <S extends Task> S save(S s);
    @Transactional
    @Modifying
    void deleteById(Integer tId);

    Optional<Task> findById(Integer tId);

    @Transactional
    @Modifying
    @Query(value = "update Task task set task.isOpen = false where task.id = ?1")
    void closeTask (Integer tId);

    @Override
    List<Task> findAll();
}
