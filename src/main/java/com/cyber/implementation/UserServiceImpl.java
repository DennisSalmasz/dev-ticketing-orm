package com.cyber.implementation;

import com.cyber.dto.UserDTO;
import com.cyber.entity.User;
import com.cyber.mapper.UserMapper;
import com.cyber.repository.UserRepository;
import com.cyber.service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    UserMapper userMapper;

    public UserServiceImpl(@Lazy UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDTO> listAllUsers() {
        List<User> list = userRepository.findAll(Sort.by("firstName"));
        //map entity into dto
        return list.stream().map(obj -> {return userMapper.convertToDto(obj);}).collect(Collectors.toList());
    }

    @Override
    public UserDTO findByUserName(String username) {

        User user = userRepository.findByUserName(username);
        return userMapper.convertToDto(user);
    }

    @Override
    public void save(UserDTO dto) {
        User obj = userMapper.convertToEntity(dto);
        //save into DB
        userRepository.save(obj);
    }

    @Override
    public UserDTO update(UserDTO dto) {

        //find current user - that has id
        User user = userRepository.findByUserName(dto.getUserName());
        //map user dto into entity object
        User convertedUser = userMapper.convertToEntity(dto);
        //set id to the converted object
        convertedUser.setId(user.getId());
        //save updated user
        userRepository.save(convertedUser);

        return  findByUserName(dto.getUserName());
    }

    @Override
    public void delete(String username) {
        User user = userRepository.findByUserName(username);
        user.setIsDeleted(true); //now, related row in DB will not be deleted !!
        userRepository.save(user);
    }

    @Override
    public void deleteByUserName(String username) {
        userRepository.deleteByUserName(username);
    }
}
