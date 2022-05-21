package telran.college.entities;
import javax.persistence.*;
@Entity
@Table(name="subjects")
public class SubjectEntity {
	@Id
long id;
	@Column(name = "subject_name", unique = true)
	String subjectName;
	public SubjectEntity() {
	}
	public SubjectEntity(long id, String subjectName) {
		this.id = id;
		this.subjectName = subjectName;
	}
	public long getId() {
		return id;
	}
	public String getSubjectName() {
		return subjectName;
	}
	

}
