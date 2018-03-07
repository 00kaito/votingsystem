package pl.akademiakodu.votingsystem.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.akademiakodu.votingsystem.model.dto.ProjectDetailsDto;
import pl.akademiakodu.votingsystem.model.dto.ProjectDto;
import pl.akademiakodu.votingsystem.model.entity.Project;
import pl.akademiakodu.votingsystem.model.entity.Vote;
import pl.akademiakodu.votingsystem.repository.ProjectRepository;
import pl.akademiakodu.votingsystem.repository.VoteRepository;

import java.util.*;


@Service
public class ProjectService {


    private ProjectRepository projectRepository;
    private VoteRepository voteRepository;
    private ModelMapper modelMapper;

    public List<ProjectDto> getListOfProjectsDto() {
        List<Project> projectList = projectRepository.findAllByOrderByProjectName();
        List<ProjectDto> projectDtoList = new ArrayList<>();
        for (Project project : projectList) {
            projectDtoList.add(modelMapper.map(project, ProjectDto.class));
        }

        return projectDtoList;
    }

    public ProjectDto changeProjectStatus(Long projectId, Boolean projectStatus) {

        ProjectDto projectDto = new ProjectDto();
        Optional<Project> projectOptional = projectRepository.findById(projectId);
        if (!projectOptional.isPresent()) {
            return projectDto;
        }
        Project project = projectOptional.get();
        project.setActive(projectStatus);
        projectRepository.save(project);
        modelMapper.map(project, projectDto);
        return projectDto;
    }

    public ProjectDetailsDto getDetails(Long projectId) {
        Optional<Project> projectOptional = projectRepository.findById(projectId);
        if (!projectOptional.isPresent()) {
            return new ProjectDetailsDto();
        }
        List<Vote> voteList = voteRepository.findAll();
        if (voteList.size() == 0) {
            return new ProjectDetailsDto();
        }
        Boolean projectVotedAtLeastOnce = false;
        for (Vote v : voteList) {
            if(v.getProject().getId().equals(projectId)){
                projectVotedAtLeastOnce=true;
            }
        }
        if (!projectVotedAtLeastOnce)
            return new ProjectDetailsDto();

        ProjectDetailsDto projectDetailsDto = new ProjectDetailsDto();
        modelMapper.map(projectOptional.get(), projectDetailsDto);
        Integer[] votesProAndAgainst = getVotesValues(projectId);
        projectDetailsDto.setVotesPro(votesProAndAgainst[0]);
        projectDetailsDto.setVotesAgainst(votesProAndAgainst[1]);
        return projectDetailsDto;
    }

    private Integer[] getVotesValues(Long projectId) {
        List<Vote> voteList = voteRepository.findAll();
        Integer votesPro = 0;
        Integer votesAgainst = 0;

        for (Vote v : voteList) {
            if (v.getProject().getId().equals(projectId) && v.getVoteValue() != 0)
                votesPro++;
            if (v.getProject().getId().equals(projectId) && v.getVoteValue() == 0)
                votesAgainst++;
        }
        return new Integer[]{votesPro, votesAgainst};
    }

    @Autowired
    public ProjectService(ProjectRepository projectRepository, VoteRepository voteRepository, ModelMapper modelMapper) {
        this.projectRepository = projectRepository;
        this.voteRepository = voteRepository;
        this.modelMapper = modelMapper;
    }
}
