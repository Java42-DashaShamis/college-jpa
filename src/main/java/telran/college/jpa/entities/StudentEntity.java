package telran.college.jpa.entities;
import javax.persistence.*;

@Entity
@Table(name = "students")
public class StudentEntity {
	@Id
	long id;
	
	String name;

	public StudentEntity(long id, String name) {
		this.id = id;
		this.name = name;
	}
	public StudentEntity() {
		
	}
	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	
}
