package telran.college.repo;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import telran.college.entities.*;
import telran.college.entities.projection.*;

public interface MarkRepository extends JpaRepository<MarkEntity, Long> {
	//@Query("select m.mark as mark from MarkEntity m where m.student.name = ?1 and m.subject.subjectname = ?2")
	List<MarkProjection> findByStudentNameAndSubjectSubjectName(String name, String subjectName);
	//@Query("select m.student.name as studentName from MarkEntity m where m.subject.subjectName = ?1 and m.mark >= ?2")
	List<StudentNameProjection> findDistinctBySubjectSubjectNameAndMarkGreaterThanEqual(String subjectName, int mark);

	@Query("select m.student.id as id, m.student.name as name from MarkEntity m "
	 		+ "group by m.student.id, m.student.name having avg(m.mark) >= "//get these fields from markentity field student, who has in related field "mark" avg of marks....
	 		+ "(select avg(m1.mark) from MarkEntity m1)")//altogether avg mark
	List<StudentProjection> findGoodStudents();
	@Query("select m.subject.id as id, m.subject.subjectName as subjectName from MarkEntity m "
			+"group by m.subject.id, m.subject.subjectName having avg(m.mark) > :avgMark")
	List<SubjectProjection> findSubjectsAvgMarkGreater(double avgMark);
}
