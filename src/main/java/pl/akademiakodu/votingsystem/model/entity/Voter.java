package pl.akademiakodu.votingsystem.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
public class Voter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String voterName;

    public Voter(String voterName) {
        this.voterName = voterName;
    }

    public Voter() {
    }
}
