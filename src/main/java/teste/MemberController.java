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
public class MemberController {

  @Autowired
  MemberRepository members;  

  @RequestMapping(value = "/member", method = POST)
  public Member save(@RequestBody Member member) {
    if(member.haveName() && member.haveAssignmentType()) {
      return members.save(member);
    }
    throw new MemberDoNotHaveNameOrAssignmentException();
  }
}

