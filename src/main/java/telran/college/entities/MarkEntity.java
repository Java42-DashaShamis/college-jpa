package telran.college.entities;
import javax.persistence.*;
@Entity
@Table(name="marks", indexes= {@Index(columnList = "student_id, subject_id")})
public class MarkEntity {
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
long id;
	int mark;
	@ManyToOne
	@JoinColumn(name = "student_id")
	StudentEntity student;
	@ManyToOne
	@JoinColumn(name = "subject_id")
	SubjectEntity subject;
	public MarkEntity() {
	}
	public MarkEntity(int mark, StudentEntity student, SubjectEntity subject) {
		this.mark = mark;
		this.student = student;
		this.subject = subject;
	}
	public long getId() {
		return id;
	}
	public int getMark() {
		return mark;
	}
	public StudentEntity getStudent() {
		return student;
	}
	public SubjectEntity getSubject() {
		return subject;
	}
	
}
