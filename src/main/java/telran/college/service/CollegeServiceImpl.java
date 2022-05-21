package telran.college.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import telran.college.dto.*;
import telran.college.entities.MarkEntity;
import telran.college.entities.StudentEntity;
import telran.college.entities.SubjectEntity;
import telran.college.repo.*;
@Service
public class CollegeServiceImpl implements CollegeService {
	StudentRepository studentsRepository;
	SubjectRepository subjectsRepository;
	MarkRepository marksRepository;
	

	public CollegeServiceImpl(StudentRepository studentsRepository, SubjectRepository subjectsRepository,
			MarkRepository marksRepository) {
		this.studentsRepository = studentsRepository;
		this.subjectsRepository = subjectsRepository;
		this.marksRepository = marksRepository;
	}

	@Override
	@Transactional
	public void addStudent(Student student) {
		if (studentsRepository.existsById(student.id)) {
			throw new RuntimeException(String.format("Student with id %d already exists", student.id));
		}
		StudentEntity studentEntity = new StudentEntity(student.id, student.name);
		studentsRepository.save(studentEntity);
		

	}

	@Override
	@Transactional
	public void addSubject(Subject subject) {
		if (subjectsRepository.existsById(subject.id)) {
			throw new RuntimeException(String.format("Subject with id %d already exists", subject.id));
		}
		SubjectEntity subjectEntity = new SubjectEntity(subject.id, subject.subjectName);
		subjectsRepository.save(subjectEntity);

	}

	@Override
	@Transactional
	public void addMark(Mark mark) {
		StudentEntity studentEntity = studentsRepository.findById(mark.stid).orElse(null);
		if (studentEntity == null) {
			throw new RuntimeException(String.format("Student with id %d doesn't exist", mark.stid));
		}
		SubjectEntity subjectEntity = subjectsRepository.findById(mark.suid).orElse(null);
		if (subjectEntity == null) {
			throw new RuntimeException(String.format("Subject with id %d doesn't exist", mark.suid));
		}
		MarkEntity markEntity = new MarkEntity(mark.mark, studentEntity, subjectEntity);
		marksRepository.save(markEntity);

	}

	@Override
	public List<Integer> getStudentMarksSubject(String name, String subjectName) {
		List<MarkEntity> markEntities =
				marksRepository.findByStudentNameAndSubjectSubjectName(name, subjectName);
		return markEntities.stream().map(MarkEntity::getMark).toList();
	}

	@Override
	public List<Student> goodCollegeStudents() {
		// TODO Auto-generated method stub not now
		return null;
	}

	@Override
	public List<Student> bestStudents(int nStudents) {
		// TODO Auto-generated method stub not now
		return null;
	}

	@Override
	public List<Student> bestStudentsSubject(int nStudents, String subjectName) {
		// TODO Auto-generated method stub not now
		return null;
	}

	@Override
	public Subject subjectGreatestAvgMark() {
		// TODO Auto-generated method stub not now
		return null;
	}

	@Override
	public void deleteStudentsAvgMarkLess(int avgMark) {
		// TODO Auto-generated method stub not now

	}

	@Override
	public List<String> getStudentsSubjectMark(String subjectName, int mark) {
		List<MarkEntity> markEntities = marksRepository.findBySubjectSubjectNameAndMarkGreaterThan(subjectName,mark);
		List<StudentEntity> studentEntities = markEntities.stream().map(MarkEntity::getStudent).distinct().toList();
		List<String> studentsNames = studentEntities.stream().map(StudentEntity::getName).toList();
		return studentsNames;
		/* V.R. It can be done a little bit shorter
		 return marksRepository
				.findBySubjectSubjectNameAndMarkGreaterThanEqual(subjectName, mark)
				.stream().map(MarkEntity::getStudent)
    			.map(StudentEntity::getName).distinct()
    			.toList();
		 */
		
	}

}
