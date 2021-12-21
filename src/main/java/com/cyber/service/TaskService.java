package com.cyber.service;

import com.cyber.dto.TaskDTO;
import com.cyber.entity.Task;

import java.util.List;

public interface TaskService {

    TaskDTO findById(Long id);
    List<TaskDTO> listAllTasks();
    void save(TaskDTO dto);
    void update(TaskDTO dto);
    void delete(Long id);
}
