package pl.akademiakodu.votingsystem.controller.restController;

import com.sun.media.sound.ModelMappedInstrument;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import pl.akademiakodu.votingsystem.model.dto.ProjectDetailsDto;
import pl.akademiakodu.votingsystem.model.dto.ProjectDto;
import pl.akademiakodu.votingsystem.model.dto.VoteDto;
import pl.akademiakodu.votingsystem.model.entity.Project;
import pl.akademiakodu.votingsystem.repository.ProjectRepository;
import pl.akademiakodu.votingsystem.service.ProjectService;
import pl.akademiakodu.votingsystem.service.VoteService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class MainRestController {

    private ProjectRepository projectRepository;
    private ProjectService projectService;
    private VoteService voteService;

    @GetMapping(value = "/api/projects")
    public ResponseEntity<List<ProjectDto>> getProjects() {
        List<ProjectDto> projectDtoList = projectService.getListOfProjectsDto();
        for (ProjectDto p : projectDtoList) {
            System.out.println(p.getProjectName());
        }

        return ResponseEntity.ok().body(projectDtoList);
    }
    @PutMapping(value = "/api/projects")
    public ResponseEntity<ProjectDto> projectStatus(@RequestParam Long projectId,
                                                          @RequestParam Boolean status) {
        ProjectDto projectDto = projectService.changeProjectStatus(projectId, status);
        return ResponseEntity.ok().body(projectDto);
    }
    @GetMapping(value = "/api/project")
    public ResponseEntity<ProjectDetailsDto> projectDetails(@RequestParam Long projectId) {
        ProjectDetailsDto projectDetailsDto = projectService.getDetails(projectId);
        return ResponseEntity.ok().body(projectDetailsDto);
    }

    @PostMapping(value = "/api/vote")
    public ResponseEntity<VoteDto> vote(@RequestParam Long voterId,
                                         @RequestParam Long projectId,
                                        @RequestParam Integer voteValue) {
        VoteDto voteDto = voteService.voting(voterId, projectId, voteValue);

        return ResponseEntity.ok().body(voteDto);
    }

    @Autowired
    public MainRestController(ProjectRepository projectRepository, ProjectService projectService, VoteService voteService) {
        this.projectRepository = projectRepository;
        this.projectService = projectService;
        this.voteService = voteService;
    }
}
