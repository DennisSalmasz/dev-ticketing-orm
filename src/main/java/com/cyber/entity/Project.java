package com.cyber.entity;

import com.cyber.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "projects")
@Where(clause = "is_deleted=false") //this will automatically be added to each ProjectRepository query !!
public class Project extends BaseEntity{

    @Column(unique = true) //if same project code entered, will crash program !!
    private String projectCode;

    private String projectName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private User assignedManager;

    private LocalDate startDate;
    private LocalDate endDate;

    private String projectDetail;

    @Enumerated(EnumType.STRING)
    private Status projectStatus;
}
