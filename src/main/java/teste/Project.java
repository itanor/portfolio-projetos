package teste;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.Embedded;
import javax.persistence.Convert;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDateTime;
import java.math.BigDecimal;

@Entity
public class Project {
   
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(columnDefinition = "serial")
  private Integer projectId;

  private String name;

  @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
  @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
  @Convert(converter = LocalDateConverter.class)
  private LocalDateTime startDate;

  @Embedded
  private Manager manager;

  @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
  @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
  @Convert(converter = LocalDateConverter.class)
  private LocalDateTime termForecast;

  @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
  @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
  @Convert(converter = LocalDateConverter.class)
  private LocalDateTime endDate;

  private BigDecimal totalBudget;

  private String projectDescription;

  @ManyToOne
  private Status status;

  public Status getStatus() {
    return status;
  }
  public void setStatus(Status status) {
    this.status = status;
  }
  public String getProjectDescription() {
    return projectDescription;
  }
  public void setProjectDescription(String description) {
    this.projectDescription = description;
  }
  public BigDecimal getTotalBudget() {
    return totalBudget;
  }
  public void setTotalBudget(BigDecimal totalBudget) {
    this.totalBudget = totalBudget;
  }

  public void setEndDate(LocalDateTime endDate) {
    this.endDate = endDate;
  }
  public LocalDateTime getEndDate() {
    return endDate;
  }

  public LocalDateTime getTermForecast() {
    return termForecast;
  }
  public void setTermForecast(LocalDateTime termForecast) {
    this.termForecast = termForecast;
  }

  public void setManager(Manager manager) {
    this.manager = manager;
  }
  public Manager getManager() {
    return manager;
  }

  public void setStartDate(LocalDateTime date) {
    this.startDate = date;
  }
  public LocalDateTime getStartDate() {
    return startDate;
  }

  public void setName(String name) {
    this.name = name;
  }
  public String getName() {
    return name;
  }

  public Integer getProjectId() {
    return projectId;
  }

  public String toString() {
    return "";
  }
}

