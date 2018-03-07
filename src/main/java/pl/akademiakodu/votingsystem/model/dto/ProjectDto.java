package pl.akademiakodu.votingsystem.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class ProjectDto {
    private Long id;
    private String projectName;
    private String projectDescription;


}
