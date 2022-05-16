package telran.college.jpa.service;

import java.util.List;

import telran.college.jpa.dto.*;

public interface CollegeService {
	void addStudent(Student student);
	void addSubject(Subject sbj);
	void addMark(Mark mark);
	
	List<Integer> getStudentMarksSubject(String name, String sbjName);
	List<Student> bestCollegeStudents();
	List<Student> bestStudents(int nStudents);
	List<Student> bestStudentsInSubject(int nStudents, long sbjID);
	Subject subjectGreatestAvgMark();
	void deleteStudentsAvgMarkLess(int avgMark);
	
	boolean exists(long id, boolean choiceOfRepo);
	boolean existsMarkOfStudentForSubject(long stID, long sbID);
}
