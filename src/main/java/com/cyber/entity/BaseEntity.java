package com.cyber.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //@Column - hibernate server validation structure
    @Column(nullable = false,updatable = false)
    private LocalDateTime insertDateTime;
    @Column(nullable = false,updatable = false)
    private Long insertUserId;

    @Column(nullable = false)
    private LocalDateTime lastUpdateDateTime;
    @Column(nullable = false)
    private Long lastUpdateUserId;

    private Boolean isDeleted = false;

    //Persist - save data in DB
    //PrePersist - this method will run before saving each data in DB
    @PrePersist
    private void onPrePersist(){
        this.insertDateTime=LocalDateTime.now();
        this.lastUpdateDateTime=LocalDateTime.now();
        //this will be dynamic - in security - who did what, when...
        this.insertUserId=1L;
        this.lastUpdateUserId=1L;
    }

    //this will run before each data update in DB
    @PreUpdate
    private void onPreUpdate(){
        this.lastUpdateDateTime=LocalDateTime.now();
        this.lastUpdateUserId=1L;
    }
}
