package telran.college.jpa.service;

import java.util.List;

import org.springframework.stereotype.Service;

import telran.college.jpa.dto.*;
import telran.college.jpa.entities.MarkEntity;
import telran.college.jpa.entities.StudentEntity;
import telran.college.jpa.entities.SubjectEntity;
import telran.college.jpa.repo.*;

@Service
public class CollegeServiceImpl implements CollegeService {
	
	StudentRepository studentRepository;
	SubjectRepository subjectRepository;
	MarkRepository markRepository;
	
	
	public CollegeServiceImpl(StudentRepository studentRepository, SubjectRepository subjectRepository,
			MarkRepository markRepository) {
		this.studentRepository = studentRepository;
		this.subjectRepository = subjectRepository;
		this.markRepository = markRepository;
	}

	@Override
	public void addStudent(Student student) {
		if(studentRepository.existsById(student.id)) {
			throw new RuntimeException(String.format("Student with id %d is already exists", student.id));
		}
		StudentEntity studentEntity = new StudentEntity(student.id, student.name);
		studentRepository.save(studentEntity);
	}

	@Override
	public void addSubject(Subject sbj) {
		
		if(studentRepository.existsById(sbj.id)) {
			throw new RuntimeException(String.format("Student with id %d is already exists", sbj.id));
		}
		SubjectEntity subjectEntity = new SubjectEntity(sbj.id, sbj.sbjName);
		subjectRepository.save(subjectEntity);
	}

	@Override
	public void addMark(Mark mark) {
		StudentEntity studentEntity = studentRepository.findById(mark.stid).orElse(null);
		if(studentEntity == null) {
			throw new RuntimeException(String.format("Student with id %d doesn't exists", mark.stid));
		}
		SubjectEntity subjectEntity = subjectRepository.findById(mark.sbid).orElse(null);
		if(subjectEntity == null) {
			throw new RuntimeException(String.format("Subject with id %d doesn't exists", mark.stid));
		}
		MarkEntity markEntity = new MarkEntity(mark.mark,studentEntity,subjectEntity);
		markRepository.save(markEntity);
	}

	@Override
	public List<Integer> getStudentMarksSubject(String name, String sbjName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> bestCollegeStudents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> bestStudents(int nStudents) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> bestStudentsInSubject(int nStudents, long sbjID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Subject subjectGreatestAvgMark() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteStudentsAvgMarkLess(int avgMark) {
		// TODO Auto-generated method stub

	}
	@Override
	public boolean exists(long id, boolean choiceOfRepo) {
		return choiceOfRepo ? studentRepository.existsById(id) : subjectRepository.existsById(id);
	}
	@Override
	public boolean existsMarkOfStudentForSubject(long stID, long sbID) {
		StudentEntity studentEntity = studentRepository.findById(stID).orElse(null);
		SubjectEntity subjectEntity = subjectRepository.findById(sbID).orElse(null);
		return markRepository.findByStIDandSbID(studentEntity, subjectEntity)!=null;
	}
}
