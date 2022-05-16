package telran.college.jpa.entities;

import javax.persistence.*;

@Entity
@Table(name = "subjects")
public class SubjectEntity {
	@Id
	long id;
	String sbjName;
	public SubjectEntity(long id, String sbjName) {
		this.id = id;
		this.sbjName = sbjName;
	}
	public SubjectEntity() {
		
	}
	public long getId() {
		return id;
	}
	public String getSbjName() {
		return sbjName;
	}
}
