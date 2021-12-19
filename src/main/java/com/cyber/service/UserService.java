package com.cyber.service;

import com.cyber.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> listAllUsers();
    UserDTO findByUserName(String username);
    void save(UserDTO dto);
    UserDTO update(UserDTO dto);
    void delete(String username);
    void deleteByUserName(String username);
    List<UserDTO> listAllByRole(String role);
}
