package com.newkeshe.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@Entity
@EqualsAndHashCode
public class Ivg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ivgId;
    @Column(length = 20)
    private String ivgName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime ivgBt;
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime ivgDur;
    private Integer ivgLocat;
    private Integer ivgNumOfTea;
    @OneToMany(mappedBy = "ivg", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
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

    public Ivg() {
    }

    public Ivg(Integer ivgId) {
        this.ivgId = ivgId;
    }
}
