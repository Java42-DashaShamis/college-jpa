package telran.college.jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import telran.college.jpa.entities.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

}
