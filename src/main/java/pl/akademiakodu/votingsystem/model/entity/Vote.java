package pl.akademiakodu.votingsystem.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.User;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(uniqueConstraints = {
        //nie moze pojawic sie druga taka sama para voter_id i project_id
        // (czyli 1 user raz moze zaglosowac na 1 projekt)
        @UniqueConstraint(columnNames = {"voter_id", "project_id"})
})

public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "voter_id")
    private Voter voter;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
    private Integer voteValue;
    private Date addedDate;

    public Vote(Voter voter, Project project, Integer voteValue, Date addedDate) {
        this.voter = voter;
        this.project = project;
        this.voteValue = voteValue;
        this.addedDate = addedDate;
    }

    public Vote() {
    }
}
