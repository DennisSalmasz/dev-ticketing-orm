package com.cyber.service;

import com.cyber.dto.ProjectDTO;

public interface ProjectService extends CrudService<ProjectDTO,String>{

    void complete(ProjectDTO project);
}
