package com.newkeshe.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Ivg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ivgId;
    @Column(nullable = false, length = 20)
    private String ivgName;
    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime ivgBt;
    @Column(nullable = false)
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime ivgDur;
    @Column(nullable = false, length = 1)
    private Integer ivgLocat;
    @Column(nullable = false, length = 5)
    private Integer ivgNumOfTea;
    @OneToMany(mappedBy = "ivg")
    private List<User_Ivg> userIvgs;
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
