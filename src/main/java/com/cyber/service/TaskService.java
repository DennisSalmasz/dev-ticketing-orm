package com.cyber.service;

import com.cyber.dto.TaskDTO;
import com.cyber.dto.UserDTO;

import java.util.List;

public interface TaskService extends CrudService<TaskDTO,Long>{

    List<TaskDTO> findTaskManager(UserDTO manager);
}
