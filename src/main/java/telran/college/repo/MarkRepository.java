package telran.college.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.college.entities.MarkEntity;

public interface MarkRepository extends JpaRepository<MarkEntity, Long> {
List<MarkEntity> findByStudentNameAndSubjectSubjectName(String name, String subjectName);

List<MarkEntity> findBySubjectSubjectNameAndMarkGreaterThan(String subjectName, int mark);
}
