package com.newkeshe.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 20)
    private String name;
    private Integer aid=1;
    private Integer role=0;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @Column(length = 11,unique = true)
    private String phone;
    @Column(length = 200)
    private String description;
    private Integer count;
    @JsonIgnore
    @OneToMany(mappedBy = "user",cascade = CascadeType.REMOVE)
    private List<User_Ivg> userIvgs;
    @JsonIgnore
    @OneToMany(mappedBy = "user",cascade = CascadeType.REMOVE)
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
    public User(){

    }
    public User(Integer uId){
        this.id = uId;
    }
}
