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
	List<StudentSubjectProjection> findGoodStudents();
	
	@Query("select m.subject.id as id, m.subject.subjectName as name from MarkEntity m "
			+"group by id, name having avg(mark) > :avgMark")
	List<StudentSubjectProjection> findSubjectsAvgMarkGreater(double avgMark);
	
	@Query(value="select s.id as id, s.name as name from marks m join students s on m.student_id = s.id "
			+"group by id, name order by avg(m.mark) desc limit :nStudents", nativeQuery = true)
	List<StudentSubjectProjection> findBestStudents(long nStudents);
	
	@Query("select distinct m.student.id as id, m.student.name as name from MarkEntity m "
			+"where m.subject.subjectName = :subject and "
			//+":mark <= all(select mark from MarkEntity where m.student.id = student.id and m.subject.id = subject.id)")
			+ "not exists (select mark from MarkEntity "
	 		+ "where m.student.id = student.id and m.subject.id = subject.id and mark < :mark)")
	List<StudentSubjectProjection> findStudentsAllMarksGreaterEqual(int mark, String subject);
	
	@Query(value = "select s.id as id, s.name as name from marks m join students s on m.student_id = s.id where m.subject_id = (select sbj.id as id from subjects sbj where sbj.subject_name = :subjectName)  "
			+"group by id, name order by avg(m.mark) desc limit :nStudents", nativeQuery = true)
	List<StudentSubjectProjection> findBestStudentsSubject(int nStudents, String subjectName);
	
	// V.R. It may be simpler
	@Query(value = "select sb.id as id, sb.subject_name as name from marks m join subjects sb on m.subject_id = sb.id "
			+"group by id, name having avg(m.mark) = "
			+"(select max(subjectsavgmark) from (select sb.id as id, avg(m.mark) as subjectsavgmark from marks m join subjects sb on m.subject_id = sb.id group by id ))", nativeQuery = true)//order by avg(m.mark) desc limit 1
	StudentSubjectProjection findSubjectGreatestAvgMark();
	
	@Query(value="select s.id as id, s.name as name from marks m join students s on m.student_id = s.id "
			+"group by id, name having count(m.mark) = "
			+"(select max(studentmarkscount) from (select s.id as id, count(m.mark) as studentmarkscount from marks m join students s on m.student_id = s.id group by id ))", nativeQuery = true)
	List<StudentSubjectProjection> findStudentsMaxMarksCount();
}
