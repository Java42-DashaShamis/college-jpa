package telran.college.jpa.entities;

import javax.persistence.*;

@Entity
@Table(name = "mark")
public class MarkEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	int mark;
	@ManyToOne
	StudentEntity student;
	@ManyToOne
	SubjectEntity subject;
	
	public MarkEntity(int mark, StudentEntity student, SubjectEntity subject) {
		this.mark = mark;
		this.student = student;
		this.subject = subject;
	}
	public MarkEntity() {
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
