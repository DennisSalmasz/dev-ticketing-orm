package com.cyber.service;

import com.cyber.dto.ProjectDTO;

import java.util.List;

public interface ProjectService {

    ProjectDTO getByProjectCode(String code);
    List<ProjectDTO> listAllProjects();
    void save(ProjectDTO dto);
    ProjectDTO update(ProjectDTO dto);
    void delete(String code);
}
