package telran.college.jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.college.jpa.entities.MarkEntity;
import telran.college.jpa.entities.StudentEntity;
import telran.college.jpa.entities.SubjectEntity;

public interface MarkRepository extends JpaRepository<MarkEntity, Long> {
	MarkEntity findByStudentAndSubject(StudentEntity student, SubjectEntity subject);
}
