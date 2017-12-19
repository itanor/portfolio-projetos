package teste;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class MemberServiceImpl implements MemberService {

  @Autowired
  MemberRepository members;

  public Member save(Member member) {
    if(member.haveName() && member.haveAssignmentType()) {
      return members.save(member);
    }
    throw new MemberDoNotHaveNameOrAssignmentException();
  }
}

