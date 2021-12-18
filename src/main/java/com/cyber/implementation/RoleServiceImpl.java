package com.cyber.implementation;

import com.cyber.dto.RoleDTO;
import com.cyber.entity.Role;
import com.cyber.mapper.RoleMapper;
import com.cyber.repository.RoleRepository;
import com.cyber.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;
    private RoleMapper roleMapper;

    public RoleServiceImpl(@Lazy RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public List<RoleDTO> listAllRoles() {

        List<Role> list = roleRepository.findAll();
        //map entity to dto
        return list.stream().map(obj -> {return roleMapper.convertToDto(obj);}).collect(Collectors.toList());
    }

    @Override
    public RoleDTO findById(Long id) {
        Role role = roleRepository.findById(id).get();
        return roleMapper.convertToDto(role);
    }
}
