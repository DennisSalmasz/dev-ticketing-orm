package com.cyber.mapper;

import com.cyber.dto.UserDTO;
import com.cyber.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    //map dto to entity
    public User convertToEntity(UserDTO dto){
        return modelMapper.map(dto,User.class);
    }

    //map entity to dto
    public UserDTO convertToDto(User entity){
        return modelMapper.map(entity,UserDTO.class);
    }
}
