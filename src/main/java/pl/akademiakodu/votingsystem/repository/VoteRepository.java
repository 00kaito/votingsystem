package pl.akademiakodu.votingsystem.repository;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.akademiakodu.votingsystem.model.entity.Project;
import pl.akademiakodu.votingsystem.model.entity.Vote;

import java.awt.print.Book;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long>{

    Vote findAllByProject_IdAndVoter_Id (Long projectId, Long voterId);
}
