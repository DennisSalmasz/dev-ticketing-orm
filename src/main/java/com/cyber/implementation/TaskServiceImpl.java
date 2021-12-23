package com.cyber.implementation;

import com.cyber.dto.ProjectDTO;
import com.cyber.dto.TaskDTO;
import com.cyber.entity.Task;
import com.cyber.entity.User;
import com.cyber.enums.Status;
import com.cyber.mapper.ProjectMapper;
import com.cyber.mapper.TaskMapper;
import com.cyber.repository.TaskRepository;
import com.cyber.repository.UserRepository;
import com.cyber.service.TaskService;
import com.cyber.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;
    private TaskMapper taskMapper;
    private ProjectMapper projectMapper;
    //private UserRepository userRepository;

    @Autowired @Lazy
    private UserRepository userRepository;

    public TaskServiceImpl(@Lazy TaskRepository taskRepository, TaskMapper taskMapper, ProjectMapper projectMapper
               //         , UserRepository userRepository
    ) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
        this.projectMapper = projectMapper;
        //this.userRepository = userRepository;
    }

    @Override
    public TaskDTO findById(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        if(task.isPresent()){
            return taskMapper.convertToDto(task.get());
        }
        return null;
    }

    @Override
    public List<TaskDTO> listAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream().map(taskMapper::convertToDto).collect(Collectors.toList());
    }

    @Override
    public void save(TaskDTO dto) {
        dto.setTaskStatus(Status.OPEN);
        dto.setAssignedDate(LocalDate.now());
        Task task = taskMapper.convertToEntity(dto);
        taskRepository.save(task);
    }

    @Override
    public void update(TaskDTO dto) {
        Optional<Task> task = taskRepository.findById(dto.getId());
        Task convertedTask = taskMapper.convertToEntity(dto);
        if(task.isPresent()){
            convertedTask.setId(task.get().getId());
            convertedTask.setTaskStatus(task.get().getTaskStatus());
            convertedTask.setAssignedDate(task.get().getAssignedDate());
            taskRepository.save(convertedTask);
        }
    }

    @Override
    public void delete(Long id) {
        Optional<Task> foundTask = taskRepository.findById(id);
        if(foundTask.isPresent()){
            foundTask.get().setIsDeleted(true);
            taskRepository.save(foundTask.get());
        }
    }

    @Override
    public int totalUncompletedTasks(String projectCode) {
        return taskRepository.totalNonCompletedTasks(projectCode);
    }

    @Override
    public int totalCompletedTasks(String projectCode) {
        return taskRepository.totalCompletedTasks(projectCode);
    }

    @Override
    public void deleteByProject(ProjectDTO project) {
        List<TaskDTO> taskList = listAllByProject(project);
        taskList.forEach(taskDTO -> delete(taskDTO.getId()));
    }

    @Override
    public List<TaskDTO> listAllByProject(ProjectDTO project) {
        return taskRepository.findAllByProject(projectMapper.convertToEntity(project))
                .stream().map(taskMapper::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> listAllTasksByStatusIsNot(Status status) {
        User user = userRepository.findByUserName("hello@bootstrap.com");
        List<Task> list = taskRepository.findAllByTaskStatusIsNotAndAssignedEmployee(status, user);
        return list.stream().map(taskMapper::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> listAllTasksByProjectManager() {
        User user = userRepository.findByUserName("deniz.salmazs@gmail.com");
        List<Task> tasks = taskRepository.findAllByProjectAssignedManager(user);
        return tasks.stream().map(taskMapper::convertToDto).collect(Collectors.toList());
    }

    @Override
    public void updateStatus(TaskDTO dto) {
        Optional<Task> task = taskRepository.findById(dto.getId());
        if(task.isPresent()){
            task.get().setTaskStatus(dto.getTaskStatus());
            taskRepository.save(task.get());
        }
    }

    @Override
    public List<TaskDTO> listAllTasksByStatus(Status status) {

        User user = userRepository.findByUserName("hello@bootstrap.com");
        List<Task> list = taskRepository.findAllByTaskStatusAndAssignedEmployee(status,user);
        return list.stream().map(taskMapper::convertToDto).collect(Collectors.toList());
    }


}
