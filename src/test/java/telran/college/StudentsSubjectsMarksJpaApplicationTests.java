package telran.college;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import telran.college.dto.Mark;
import telran.college.dto.Student;
import telran.college.dto.Subject;
import telran.college.service.CollegeService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudentsSubjectsMarksJpaApplicationTests {
@Autowired
	CollegeService collegeService;
	@Test
	@Order(1)
	void addMarks() {
		collegeService.addMark(new Mark(1,1,70));
		collegeService.addMark(new Mark(1,1,80));
		collegeService.addMark(new Mark(1,1,90));
		collegeService.addMark(new Mark(2,1,50));
		collegeService.addMark(new Mark(2,1,80));
		collegeService.addMark(new Mark(3,1,40));
		collegeService.addMark(new Mark(1,2,90));
		collegeService.addMark(new Mark(1,2,60));
		collegeService.addMark(new Mark(1,2,70));
		collegeService.addMark(new Mark(2,2,30));
		collegeService.addMark(new Mark(3,2,40));
		collegeService.addMark(new Mark(3,2,70));
	}
	@Test
	@Order(2)
	void getMarksStudentSubjectTest() {
		List<Integer> expected = Arrays.asList(70, 80, 90);
		List<Integer> actual = collegeService.getStudentMarksSubject("student1", "subject1");
		assertIterableEquals(expected, actual);
	}
	@Test
	@Order(3)
	void getStudentsSubjectMarks() {
		List<String> expected = Arrays.asList("student1","student2");
		List<String> actual = collegeService.getStudentsSubjectMark("subject1", 70);
		assertIterableEquals(expected,actual); 
	}
	@Test
	@Order(4)
	void getGoodStudents() {
		List<Student> expected = Arrays.asList(new Student(1,"student1"));
		List<Student> actual = collegeService.goodCollegeStudents();
		assertIterableEquals(expected, actual);
	}
	@Test
	@Order(5)
	void deleteStudents() { //avgMarks: student1 - 76, student2 - 53, student3 - 50
		collegeService.deleteStudentsAvgMarkLess(51);  //delete student3
		List<String> actual = collegeService.getStudentsSubjectMark("subject2", 20);
		assertEquals(2,actual.size());
	}
	@Test
	@Order(6)
	void deleteStudentsCountMarksLess() { //delete student2
		List<Student> students = collegeService.deleteStudentsMarksCountLess(5);
		assertEquals(1,students.size());
		assertEquals(2,students.get(0).id); 
		/* V.R.
		 * Using assertIterableEquals(expected, actual) looks more convincing
		 */
	}
	@Test
	@Order(6)
	void getSubjectsAvgMarkGreater() { 
		collegeService.addMark(new Mark(4,1,70));
		collegeService.addMark(new Mark(4,1,50));
		collegeService.addMark(new Mark(4,2,40));
		collegeService.addMark(new Mark(4,2,80));
		
		List<Subject> actual = collegeService.subjectsAvgMarkGreater(70); //subject1 - 72 subject2 - 68
		assertEquals(1,actual.size());
		assertEquals(1,actual.get(0).id);
		/* V.R.
		 * Using assertIterableEquals(expected, actual) looks more convincing
		 */
	}
}
