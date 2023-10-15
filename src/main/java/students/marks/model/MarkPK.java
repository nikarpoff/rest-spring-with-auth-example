package students.marks.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class MarkPK implements Serializable {

    @JsonProperty("student_id")
    @Column(name = "student_id")
    private Long studentId = null;

    @JsonProperty("lab_num")
    @Column(name = "lab_num")
    private int labNum = 0;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public int getLabNum() {
        return labNum;
    }

    public void setLabNum(int labNum) {
        this.labNum = labNum;
    }
}
