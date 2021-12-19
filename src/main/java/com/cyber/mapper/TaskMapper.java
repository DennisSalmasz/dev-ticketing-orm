package com.cyber.mapper;

import com.cyber.dto.TaskDTO;
import com.cyber.entity.Task;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    private ModelMapper modelMapper;

    public TaskMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    //map dto to entity
    public Task convertToEntity(TaskDTO dto){
        return modelMapper.map(dto, Task.class);
    }

    //map entity to dto
    public TaskDTO convertToDto(Task entity){
        return modelMapper.map(entity,TaskDTO.class);
    }
}
