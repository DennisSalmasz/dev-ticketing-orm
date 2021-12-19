package com.cyber.mapper;

import com.cyber.dto.ProjectDTO;
import com.cyber.entity.Project;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {

    private ModelMapper modelMapper;

    public ProjectMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    //map dto to entity
    public Project convertToEntity(ProjectDTO dto){
        return modelMapper.map(dto, Project.class);
    }

    //map entity to dto
    public ProjectDTO convertToDto(Project entity){
        return modelMapper.map(entity,ProjectDTO.class);
    }
}
