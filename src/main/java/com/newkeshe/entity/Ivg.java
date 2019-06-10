package com.newkeshe.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Integer id;
    @Column(length = 20)
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime beginTime;
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime duration;
    private Integer location;
    private Integer numbersOfTeacher;
    private Integer count;
    @JsonIgnore
    @OneToMany(mappedBy = "ivg", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<User_Ivg> userIvgs;
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

    public Ivg() {
    }

    public Ivg(Integer ivgId) {
        this.id = ivgId;
    }
}
