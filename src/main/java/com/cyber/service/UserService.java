package com.cyber.service;

import com.cyber.dto.UserDTO;

import java.util.List;

public interface UserService extends CrudService<UserDTO,String> {

    List<UserDTO> findManagers();
    List<UserDTO> findEmployees();
}
