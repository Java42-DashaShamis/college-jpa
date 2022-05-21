package telran.college.entities;

import javax.persistence.*;

@Entity
@Table(name = "students")
public class StudentEntity {
	@Id
	long id;
	@Column(unique = true)
	String name;
	public StudentEntity(long id, String name) {
		this.id = id;
		this.name = name;
	}
	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public StudentEntity() {
	}
}
