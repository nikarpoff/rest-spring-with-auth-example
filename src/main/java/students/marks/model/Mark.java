package students.marks.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

/**
 * object with info about student's mark
 */
@Table(name = "MARK")
@Entity
public class Mark   {
  @JsonProperty("mark_pk")
  @EmbeddedId
  MarkPK markPK = new MarkPK();

  @JsonProperty("value")
  private int value = 0;

  public Mark id(MarkPK id) {
    this.markPK = id;
    return this;
  }

  public MarkPK getId() {
    return markPK;
  }

  public void setId(MarkPK id) {
    this.markPK = id;
  }

  public Mark studentId(Long studentId) {
    this.markPK.setStudentId(studentId);
    return this;
  }

  public Long getStudentId() {
      return markPK.getStudentId();
  }

  public void setStudentId(Long studentId) {
    this.markPK.setStudentId(studentId);
  }

  public Mark labNum(int labNum) {
    this.markPK.setLabNum(labNum);
    return this;
  }

  public int getLabNum() {
      return markPK.getLabNum();
  }

  public void setLabNum(int labNum) {
    this.markPK.setLabNum(labNum);
  }

  public Mark value(int value) {
    this.value = value;
    return this;
  }
  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Mark mark = (Mark) o;
    return
        Objects.equals(this.markPK.getStudentId(), mark.getStudentId()) &&
        Objects.equals(this.markPK.getLabNum(), mark.getLabNum()) &&
        Objects.equals(this.value, mark.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(markPK.getStudentId(), markPK.getLabNum(), value);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Mark {\n");
    sb.append("    studentId: ").append(toIndentedString(markPK.getStudentId())).append("\n");
    sb.append("    labNum: ").append(toIndentedString(markPK.getLabNum())).append("\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
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
