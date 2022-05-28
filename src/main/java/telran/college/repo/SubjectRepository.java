package telran.college.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import telran.college.entities.SubjectEntity;
import telran.college.entities.projection.StudentSubjectProjection;
import telran.college.entities.projection.SubjectProjection;

public interface SubjectRepository extends JpaRepository<SubjectEntity, Long> {
	@Query("select sb.id as id, sb.subjectName as name from SubjectEntity sb where sb.id in "
			+ "(select msb.id from MarkEntity m right join m.subject msb group by msb.id having avg(m.mark) < :avgMark)")
	List<StudentSubjectProjection> findSubjectsAvgMarkLess(double avgMark);
}
