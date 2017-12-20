package teste;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ProjectServiceImpl implements ProjectService {

  @Autowired
  ProjectRepository projects;

  @Autowired
  StatusRepository status;

  public Project save(Project project) {
    if(project.isNew()) {
      project.setStatus(status.findById(new Long(Status.STARTED)));
    }
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

