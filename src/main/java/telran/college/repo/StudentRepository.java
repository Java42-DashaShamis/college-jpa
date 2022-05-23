package telran.college.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import telran.college.entities.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
	@Query("select s from StudentEntity s where s.id in"
			+"(select m.student.id from MarkEntity m group by m.student.id having avg(m.mark) < :avgMark)")
	List<StudentEntity> getStudentsAvgMarkLess(double avgMark);
	@Query("select s from StudentEntity s where s.id in"
			+"(select m.student.id from MarkEntity m group by m.student.id having count(m.mark) < :count)")
	List<StudentEntity> getStudentsCountMarkLess(long count);
}
