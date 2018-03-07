package pl.akademiakodu.votingsystem.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.akademiakodu.votingsystem.model.dto.VoteDto;
import pl.akademiakodu.votingsystem.model.entity.Project;
import pl.akademiakodu.votingsystem.model.entity.Vote;
import pl.akademiakodu.votingsystem.model.entity.Voter;
import pl.akademiakodu.votingsystem.repository.ProjectRepository;
import pl.akademiakodu.votingsystem.repository.VoteRepository;
import pl.akademiakodu.votingsystem.repository.VoterRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class VoteService {

    private VoteRepository voteRepository;
    private VoterRepository voterRepository;
    private ProjectRepository projectRepository;
    private ModelMapper modelMapper;

    @Autowired
    public VoteService(VoteRepository voteRepository,
                       VoterRepository voterRepository,
                       ProjectRepository projectRepository,
                       ModelMapper modelMapper) {
        this.voteRepository = voteRepository;
        this.voterRepository = voterRepository;
        this.projectRepository = projectRepository;
        this.modelMapper = modelMapper;
    }


    public VoteDto voting(Long voterId, Long projectId, Integer voteValue) {
        Vote vote = new Vote();
        if (!projectRepository.findById(projectId).isPresent() || !voterRepository.findById(voterId).isPresent()) {
            return modelMapper.map(vote, VoteDto.class);
        }
        List<Vote> voteList = voteRepository.findAll();
        if (voteList.size() == 0) {
            return modelMapper.map(vote, VoteDto.class);
        }
        for (Vote v : voteList) {
            if (v.getProject().getId().equals(v.getVoter().getId()) || !v.getProject().getActive()) {
                return modelMapper.map(vote, VoteDto.class);
            }
        }
        Optional<Voter> voterOptional = voterRepository.findById(voterId);
        Optional<Project> projectOptional = projectRepository.findById(projectId);
        vote = new Vote(voterOptional.get(), projectOptional.get(), voteValue, new Date());
        voteRepository.save(vote);
        return modelMapper.map(vote, VoteDto.class);
    }
}
