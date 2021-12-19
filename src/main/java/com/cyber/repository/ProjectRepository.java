package com.cyber.repository;

import com.cyber.entity.Project;
import com.cyber.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project,Long> {

    Project findByProjectCode(String code);
    List<Project> findByAssignedManager(User manager);


}
