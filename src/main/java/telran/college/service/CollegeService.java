package telran.college.service;

import java.util.List;

import telran.college.dto.*;

public interface CollegeService {
void addStudent(Student student);
void addSubject(Subject subject);
void addMark(Mark mark);
List<String> getStudentsSubjectMark(String subjectName, int mark); //get student names those who have at least one mark 
// greater or equal the given one
List<Integer> getStudentMarksSubject(String name, String subjectName);
List<Student> goodCollegeStudents();//students with avg mark greater than total avg mark of the college
List<Student> bestStudents(int nStudents); //the given number of the best students
List<Student> bestStudentsSubject(int nStudents, String subjectName);
Subject subjectGreatestAvgMark();
void deleteStudentsAvgMarkLess(int avgMark);//remove all students having avg mark less than the given one
}
