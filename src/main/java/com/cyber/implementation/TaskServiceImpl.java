package com.cyber.implementation;

import com.cyber.dto.TaskDTO;
import com.cyber.entity.Task;
import com.cyber.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Override
    public TaskDTO findById(Long id) {
        return null;
    }

    @Override
    public List<TaskDTO> listAllTasks() {
        return null;
    }

    @Override
    public Task save(TaskDTO dto) {
        return null;
    }

    @Override
    public void update(TaskDTO dto) {

    }

    @Override
    public void delete(Long id) {

    }
}
