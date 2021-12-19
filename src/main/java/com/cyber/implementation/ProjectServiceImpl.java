package com.cyber.implementation;

import com.cyber.dto.ProjectDTO;
import com.cyber.entity.Project;
import com.cyber.enums.Status;
import com.cyber.mapper.ProjectMapper;
import com.cyber.mapper.UserMapper;
import com.cyber.repository.ProjectRepository;
import com.cyber.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    ProjectRepository projectRepository;
    ProjectMapper projectMapper;
    private UserMapper userMapper;

    public ProjectServiceImpl(@Lazy ProjectRepository projectRepository, ProjectMapper projectMapper, UserMapper userMapper) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
        this.userMapper = userMapper;
    }

    @Override
    public ProjectDTO getByProjectCode(String code) {
        return null;
    }

    @Override
    public List<ProjectDTO> listAllProjects() {
        List<Project> projects = projectRepository.findAll();
        return projects.stream().map(obj -> {return projectMapper.convertToDto(obj);}).collect(Collectors.toList());
    }

    @Override
    public void save(ProjectDTO dto) {
        dto.setProjectStatus(Status.OPEN);
        Project obj = projectMapper.convertToEntity(dto);
        //need to convert assigned manager to entity as well - it is dependant !!
        //obj.setAssignedManager(userMapper.convertToEntity(dto.getAssignedManager()));
        projectRepository.save(obj);
    }

    @Override
    public ProjectDTO update(ProjectDTO dto) {
        return null;
    }

    @Override
    public void delete(String code) {

    }
}
