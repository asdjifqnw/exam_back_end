package com.newkeshe.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uId;
    @Column(nullable = false, length = 20)
    private String uName;
    @Column(nullable = false)
    private Integer uPerm;
    @Column(nullable = false)
    private Integer uPosit;
    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String uPwd;
    @Column(nullable = false, length = 11,unique = true)
    private String uPhone;
    @Column(nullable = false, length = 200)
    private String uDesc;
    @OneToMany(mappedBy = "user")
    private List<User_Ivg> userIvgs;
    @OneToMany(mappedBy = "user")
    private List<User_Task> userTasks;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false,
            columnDefinition = "DATETIME NOT NULL " +
                    "DEFAULT CURRENT_TIMESTAMP",
            updatable = false,
            insertable = false)
    private LocalDateTime insTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false,
            columnDefinition = "DATETIME NOT NULL " +
                    "DEFAULT CURRENT_TIMESTAMP ON UPDATE " +
                    "CURRENT_TIMESTAMP",
            updatable = false,
            insertable = false)
    private LocalDateTime upTime;
}
