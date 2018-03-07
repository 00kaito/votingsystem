package pl.akademiakodu.votingsystem.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.akademiakodu.votingsystem.model.entity.Project;
import pl.akademiakodu.votingsystem.model.entity.Voter;

@Getter @Setter @ToString
public class VoteDto {
    private Long id;
    private Voter voter;
    private Project project;
    private Integer voteValue;
}
