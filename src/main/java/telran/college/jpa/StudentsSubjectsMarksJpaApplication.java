package telran.college.jpa;


import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import telran.college.jpa.dto.Mark;
import telran.college.jpa.dto.Student;
import telran.college.jpa.dto.Subject;
import telran.college.jpa.service.CollegeService;
import static telran.college.jpa.api.ApiConstants.*;

@SpringBootApplication
public class StudentsSubjectsMarksJpaApplication {
	@Value("${app.students.num: 0}")
	int nStudents;
	@Value("${app.subjects.num: 0}")
	int nSubjects;
	@Value("${app.marks.num: 0}")
	int nMarks;
	@Autowired
	CollegeService service;
	
	LinkedList<Long> listOfStIDs = new LinkedList<Long>();
	LinkedList<Long> listOfSbIDs = new LinkedList<Long>();
	
	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(StudentsSubjectsMarksJpaApplication.class, args);
		
	}
	@PostConstruct
	void createDb() {
		createStudents();
		createSubjects();
		createMarks();
	}
	void createStudents() {
		for(int i = 1; i<=nStudents; i++) {
			Student student = new Student(getId(true),"student"+i);
			service.addStudent(student);
			listOfStIDs.add(student.id);
		}
	}
	void createSubjects() {
		for(int i = 1; i<=nSubjects; i++) {
			Subject subject = new Subject(getId(false),"subject"+i);
			service.addSubject(subject);
			listOfSbIDs.add(subject.id);
		}
	}
	void createMarks() {
		for(int i = 1; i<=nMarks; i++) {
			long stID;
			long sbID;
			do {
				stID = getStOrSbID(true);
				sbID = getStOrSbID(false);
			}while(service.existsMarkOfStudentForSubject(stID, sbID));
			Mark mark = new Mark(stID, sbID, getMark());
			service.addMark(mark);
		}
	}
	
	protected long getId(boolean choiceOfRepo) {
		long id = 0;
		var threadLocal = ThreadLocalRandom.current();
		do {
			id = threadLocal.nextLong(MIN_ID,MAX_ID);
		}while(service.exists(id, choiceOfRepo));
		return id;
	}
	protected int getMark() {
		var threadLocal = ThreadLocalRandom.current();
		return threadLocal.nextInt(MIN_MARK, MAX_MARK);
	}
	protected long getStOrSbID(boolean choiceOfRepo) {
		long id = 0;
		var threadLocal = ThreadLocalRandom.current();
		do {
			if(choiceOfRepo ? !listOfStIDs.isEmpty() : !listOfSbIDs.isEmpty()) {
				id = choiceOfRepo ? listOfStIDs.get(threadLocal.nextInt(listOfStIDs.size())) : listOfSbIDs.get(threadLocal.nextInt(listOfSbIDs.size()));
			}
			
		}while(service.exists(id, choiceOfRepo));
		
		return id;
	}
}
