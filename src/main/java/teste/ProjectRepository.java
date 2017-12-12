package teste;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
  
  public Project findByProjectId(Integer projectId);
  public List<Project> findAll();
}

