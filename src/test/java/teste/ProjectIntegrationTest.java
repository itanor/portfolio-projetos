package teste;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Bean;
import org.springframework.boot.test.context.TestConfiguration;

@RunWith(SpringRunner.class)
@DataJpaTest
@IntegrationTestConfig
public class ProjectIntegrationTest {

  @TestConfiguration
  static class TestContextConfiguration {
          
    @Bean
    public ProjectService projectService() {
      return new ProjectServiceImpl();
    }
    @Bean
    public MemberService memberService() {
      return new MemberServiceImpl();
    }
  }

  @Autowired
  ProjectService projectService;

  @Autowired
  MemberService memberService;

  @Test
  public void mustSaveAProject() {
    assertNotNull(projectService.save(new Project()));
  }

  @Test(expected = ProjectStartedProgressFinishedException.class)
  public void doNotDeleteWhenStartedProgressOrFinished() {
    Project p = new Project();
    Status started = new Status(4L);
    p.setStatus(started);

    projectService.delete(p);
  }

  @Test(expected = MemberDoNotHaveNameOrAssignmentException.class)
  public void doNotSaveMemberWithoutName() {
    Member member = new Member("", Member.AssignmentType.EMPLOYEE);
    memberService.save(member);
  }

  @Test(expected = MemberDoNotHaveNameOrAssignmentException.class)
  public void doNotSaveMemberWithoutAssignmentType() {
    Member member = new Member("John Doe", null);
    memberService.save(member);
  }
}

