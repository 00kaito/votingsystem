package pl.akademiakodu.votingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.akademiakodu.votingsystem.model.entity.Project;
import pl.akademiakodu.votingsystem.model.entity.Voter;

@Repository
public interface VoterRepository extends JpaRepository<Voter, Long>{
}
