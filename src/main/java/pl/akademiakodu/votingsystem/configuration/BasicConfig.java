package pl.akademiakodu.votingsystem.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.akademiakodu.votingsystem.model.dto.ProjectDetailsDto;
import pl.akademiakodu.votingsystem.model.dto.ProjectDto;
import pl.akademiakodu.votingsystem.model.dto.VoteDto;
import pl.akademiakodu.votingsystem.model.entity.Project;
import pl.akademiakodu.votingsystem.model.entity.Vote;

@Configuration
public class BasicConfig {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(Project.class, ProjectDto.class);
        modelMapper.map(Vote.class, VoteDto.class);

        modelMapper.map(Project.class, ProjectDetailsDto.class);

        return modelMapper;
    }
}
