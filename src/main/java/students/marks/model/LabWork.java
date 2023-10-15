package students.marks.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Objects;

/**
 * object with info about subject
 */
@Table(name = "LAB_WORK")
@Entity
public class LabWork {
  @Id
  @GeneratedValue
  @JsonProperty("id")
  @Column(name = "id")
  private Integer id = null;

  @JsonProperty("lab_num")
  @Column(name = "lab_num", unique = true)
  private int labNum = 0;

  public LabWork id(Integer id) {
    this.id = id;
    return this;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }
  
  public int getLabNum() {
    return labNum;
  }

  public void setLabNum(int id) {
    this.labNum = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LabWork labWork = (LabWork) o;
    return Objects.equals(this.labNum, labWork.labNum);
  }

  @Override
  public int hashCode() {
    return Objects.hash(labNum);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Subject {\n");
    
    sb.append("    id: ").append(toIndentedString(labNum)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
