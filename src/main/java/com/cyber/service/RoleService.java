package com.cyber.service;

import com.cyber.dto.RoleDTO;

public interface RoleService extends CrudService<RoleDTO,Long>{

    // In RoleDTO unique property is [Long id] --- PK in DB !!
}
