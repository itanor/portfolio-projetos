
package teste;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.io.Serializable;

@Entity
public class Member implements Serializable {

  @Id
  //@GeneratedValue(strategy = GenerationType.IDENTITY)
  @GeneratedValue(strategy = GenerationType.AUTO)
  //@Column(columnDefinition = "serial")
  private Long id;

  private String name;

  private AssignmentType assignment;

  public Member() {}

  public Member(String name, AssignmentType a) {
    this.name = name;
    this.assignment = a;
  }

  public AssignmentType getAssignmentType() {
    return assignment;
  }
  public void setAssignmentType(AssignmentType t) {
    this.assignment = t;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }

  public enum AssignmentType {
    EMPLOYEE("employee"), 
    MANAGER("manager");

    String type;

    AssignmentType(String type) {
      this.type = type;
    }

    public String getType() {
      return type;
    }
  }

  public boolean haveName() {
    return name != null && !"".equals(name.trim());
  }

  public boolean haveAssignmentType() {
    return assignment != null;
  }

  public boolean isEmployee() {
    return assignment != null 
      && AssignmentType.EMPLOYEE.equals(assignment);
  }
}

