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
	void getMarksStudentSubjectTest() {
		List<Integer> expected = Arrays.asList(70, 80, 90);
		List<Integer> actual = collegeService.getStudentMarksSubject("student1", "subject1");
		assertIterableEquals(expected, actual);
	}
	@Test
	void getStudentsSubjectMarks() {
		List<String> expected = Arrays.asList("student1","student2");
		List<String> actual = collegeService.getStudentsSubjectMark("subject1", 70);
		assertIterableEquals(expected,actual); 
	}

}
