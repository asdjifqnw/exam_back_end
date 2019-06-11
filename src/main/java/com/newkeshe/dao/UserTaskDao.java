package com.newkeshe.dao;

import com.newkeshe.entity.Task;
import com.newkeshe.entity.User;
import com.newkeshe.entity.User_Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserTaskDao extends JpaRepository<User_Task,Integer> {
    @Override
    <S extends User_Task> S save(S s);
    @Transactional
    @Modifying
    void deleteByUserAndTask(User user,Task task);

    List<User_Task> findByUserAndTask(User user,Task task);

    @Transactional
    @Modifying
    void deleteById(Integer id);

    List<User_Task> findByUser(User user);
    List<User_Task> findByTask(Task task);
}
