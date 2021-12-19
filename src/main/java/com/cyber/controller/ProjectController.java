package com.cyber.controller;

import com.cyber.dto.ProjectDTO;
import com.cyber.dto.TaskDTO;
import com.cyber.dto.UserDTO;
import com.cyber.enums.Status;
import com.cyber.service.ProjectService;
import com.cyber.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/project")
public class ProjectController {

    private ProjectService projectService;
    private UserService userService; // we just basically put all the users as managers !!

    public ProjectController(ProjectService projectService, UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }

//    @Autowired
//    TaskService taskService;

    @GetMapping("/create")
    public String createProject(Model model){

        model.addAttribute("project", new ProjectDTO());
        model.addAttribute("managers",userService.listAllByRole("manager")); // assign managers
        model.addAttribute("projects",projectService.listAllProjects());

        return "/project/create";
    }

    @PostMapping("/create")
    public String insertProject(ProjectDTO project, Model model){
        projectService.save(project);
        return "redirect:/project/create";
    }

//    @GetMapping("/delete/{projectCode}")
//    public String deleteProject(@PathVariable("projectCode") String projectCode){
//        projectService.deleteById(projectCode);
//        return "redirect:/project/create";
//    }
//
//    @GetMapping("/complete/{projectCode}")
//    public String completeProject(@PathVariable("projectCode") String projectCode){
//        projectService.complete(projectService.findById(projectCode));
//        return "redirect:/project/create";
//    }
//
//    @GetMapping("/update/{projectCode}")
//    public String editProject(@PathVariable("projectCode") String projectCode, Model model){
//
//        model.addAttribute("project", projectService.findById(projectCode));
//        model.addAttribute("managers",userService.findManagers());
//        model.addAttribute("projects",projectService.findAll());
//
//        return "/project/update";
//    }
//
//    @PostMapping ("/update/{projectCode}")
//    public String updateProject(@PathVariable("projectCode") String projectCode, ProjectDTO project, Model model){
//        projectService.update(project);
//        return "redirect:/project/create";
//    }
//
//    @GetMapping("/manager/complete")
//    public String getProjectsByManager(Model model){
//
//        //I wanna see project belong to John
//        UserDTO manager = userService.findById("john@ticketng.com");
//
//        List<ProjectDTO> projects = getCountedListOfProjectDTO(manager);
//        model.addAttribute("projects",projects);
//
//        return "manager/project-status";
//    }
//
//    List<ProjectDTO> getCountedListOfProjectDTO(UserDTO manager){
//
//        List<ProjectDTO> list = projectService
//                .findAll()
//                .stream()
//                .filter(p -> p.getAssignedManager().equals(manager))
//                .map(p -> {
//                    List<TaskDTO> taskList = taskService.findTaskManager(manager);
//                    int completeCount = (int) taskList.stream().filter( t -> t.getProject().equals(p) && t.getTaskStatus() == Status.COMPLETE).count();
//                    int incompleteCount = (int) taskList.stream().filter( t -> t.getProject().equals(p) && t.getTaskStatus() != Status.COMPLETE).count();
//
//                    p.setCompleteTaskCount(completeCount);
//                    p.setIncompleteTaskCount(incompleteCount);
//
//                    return p;
//
//        }).collect(Collectors.toList());
//        return list;
//    }
}
