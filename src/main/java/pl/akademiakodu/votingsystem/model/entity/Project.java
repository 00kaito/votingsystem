package pl.akademiakodu.votingsystem.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String projectName;
    private String projectDescription;
    private Boolean active;

    public Project(String projectName, String projectDescription, Boolean active) {
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.active = active;
    }

    public Project() {
    }

    public Project(Boolean active) {
        this.active = active;
    }
}
