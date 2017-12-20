package teste;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Status {

  public static final int STARTED  = 4;
  public static final int PROGRESS = 6;
  public static final int FINISHED = 7;

  @Id
  //@GeneratedValue(strategy = GenerationType.IDENTITY)
  @GeneratedValue(strategy = GenerationType.AUTO)
  //@Column(columnDefinition = "serial")
  private Long id;

  @Column(length = 255)
  private String description;

  public Status() {}

  public Status(Long id) {
    this.id = id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Long getId() {
    return id;
  }

  public static Status started() {
    return new Status(new Long(STARTED));
  }
}

