package teste;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class ProjectTest {

  Project project;

  @Before
  public void setUp() {
    project = new Project();
  }

  @Test
  public void statusNotNull() {
    Status started = new Status();
    project.setStatus(started);
    assertNotNull(project.getStatus()); 
  }

  @Test(expected = IllegalArgumentException.class)
  public void doNotAssociateMemberDifferentThanEmployee() {
    project.setMembers(new ArrayList<>());

    Member m = new Member("Marie", Member.AssignmentType.MANAGER);
    project.toAssociate(m);
  }

  @Test
  public void associateEmployeeMember() {
    project.setMembers(new ArrayList<>());

    Member m = new Member("Pedro", Member.AssignmentType.EMPLOYEE);
    project.toAssociate(m);

    assertTrue(project.getMembers().size() == 1);
  }
}

