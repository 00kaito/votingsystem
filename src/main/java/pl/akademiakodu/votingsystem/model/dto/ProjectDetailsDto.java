package pl.akademiakodu.votingsystem.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class ProjectDetailsDto {
    private String projectName;
    private String projectDescription;
    private Integer votesPro;
    private Integer votesAgainst;

}
