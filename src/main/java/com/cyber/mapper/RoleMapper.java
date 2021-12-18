package com.cyber.mapper;

import com.cyber.dto.RoleDTO;
import com.cyber.entity.Role;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    private ModelMapper modelMapper;

    public RoleMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    //map dto to entity
    public Role convertToEntity(RoleDTO dto){
        return modelMapper.map(dto,Role.class);
    }

    //map entity to dto
    public RoleDTO convertToDto(Role entity){
        return modelMapper.map(entity,RoleDTO.class);
    }
}
