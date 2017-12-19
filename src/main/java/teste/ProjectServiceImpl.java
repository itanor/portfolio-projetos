package teste;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ProjectServiceImpl implements ProjectService {

  @Autowired
  ProjectRepository projects;

  public Project save(Project project) {
    return projects.save(project);
  }

  public void delete(Project project) {
    if(!project.canDelete()) {
      throw new ProjectStartedProgressFinishedException();
    }
    projects.delete(project.getProjectId());
    projects.flush();
  }
}

