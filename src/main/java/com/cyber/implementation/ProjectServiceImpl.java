package com.cyber.implementation;

import com.cyber.dto.ProjectDTO;
import com.cyber.entity.Project;
import com.cyber.enums.Status;
import com.cyber.mapper.ProjectMapper;
import com.cyber.mapper.UserMapper;
import com.cyber.repository.ProjectRepository;
import com.cyber.service.ProjectService;
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
        Project project = projectRepository.findByProjectCode(code);
        return projectMapper.convertToDto(project);
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
    public void update(ProjectDTO dto) {
        Project project = projectRepository.findByProjectCode(dto.getProjectCode());
        Project convertedProject = projectMapper.convertToEntity(dto);
        convertedProject.setId(project.getId());
        convertedProject.setProjectStatus(project.getProjectStatus());
        projectRepository.save(convertedProject);
    }

    @Override
    public void delete(String code) {
        Project project = projectRepository.findByProjectCode(code);
        project.setIsDeleted(true); //now, related row in DB will not be deleted !!
        projectRepository.save(project);
    }

    @Override
    public void complete(String code) {
        Project project = projectRepository.findByProjectCode(code);
        project.setProjectStatus(Status.COMPLETE);
        projectRepository.save(project);
    }
}
