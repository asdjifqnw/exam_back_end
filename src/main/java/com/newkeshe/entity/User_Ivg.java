package com.newkeshe.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
@Getter
@Setter
@EqualsAndHashCode
@Entity
@NoArgsConstructor
public class User_Ivg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    private Ivg ivg;
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
}
