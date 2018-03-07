package pl.akademiakodu.votingsystem;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.akademiakodu.votingsystem.model.entity.Project;
import pl.akademiakodu.votingsystem.model.entity.Voter;
import pl.akademiakodu.votingsystem.repository.ProjectRepository;
import pl.akademiakodu.votingsystem.repository.VoterRepository;

import static org.junit.Assert.assertNotNull; //import metody statycznej

//testy z kontekstem springa (mozna wstrzykiwac np repozytoria)
@RunWith(SpringRunner.class)
@SpringBootTest
public class VotingsystemApplicationTests {

    @Autowired
    private VoterRepository voterRepository;
    @Autowired
    private ProjectRepository projectRepository;

    @Test
    @Ignore // test nie bedzie wykonywany przy budowaniu aplikacji
    public void createVoters() {
        for(int i=0; i<5; i++){
            voterRepository.save(new Voter("voter "+i));
        }
    }

    @Test
    @Ignore
    public void createProjects() {
        for(int i=0; i<5; i++){
            projectRepository.save(new Project("Project "+i,
                    "Description of project "+i,
                    true));
        }
    }

}
