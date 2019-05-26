package com.newkeshe.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@EqualsAndHashCode
@ToString
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer type;
    private String description;
    @Column(length = 20)
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime ddl;
    private Boolean isOpen;
    @OneToMany(mappedBy = "task",cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
    private List<User_Task> userTasks;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false,
            columnDefinition = "DATETIME NOT NULL " +
                    "DEFAULT CURRENT_TIMESTAMP",
            updatable = false,
            insertable = false)
    private LocalDateTime insertTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false,
            columnDefinition = "DATETIME NOT NULL " +
                    "DEFAULT CURRENT_TIMESTAMP ON UPDATE " +
                    "CURRENT_TIMESTAMP",
            updatable = false,
            insertable = false)
    private LocalDateTime updateTime;
    public Task(Integer tId){
        this.id = tId;
    }
    public Task(){}
}
