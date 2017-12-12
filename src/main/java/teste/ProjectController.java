package teste;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
public class ProjectController {

  @Autowired
  ProjectRepository projects;  

  @RequestMapping(value = "/project", method = GET)
  public List<Project> list() {
    return projects.findAll();
  }

  @RequestMapping(value = "/project", method = POST)
  public Project save(@RequestBody Project project) {
    return projects.save(project);
  }

  @RequestMapping(value = "/project/{id}", method = DELETE)
  public void delete(@PathVariable Integer id) {
    projects.delete(id);
  }
}

