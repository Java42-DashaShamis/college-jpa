package telran.college.service;

import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import telran.college.dto.*;
import telran.college.entities.*;
import telran.college.entities.projection.*;
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
		return marksRepository.findByStudentNameAndSubjectSubjectName(name, subjectName).stream().map(MarkProjection::getMark).toList();
	}

	@Override
	public List<Student> goodCollegeStudents() {
		return marksRepository.findGoodStudents().stream().map(sp -> new Student(sp.getId(),sp.getName())).toList();
	}

	@Override
	public List<Student> bestStudents(int nStudents) { //NativeSQL
		return marksRepository.findBestStudents(nStudents).stream().map(sp -> new Student(sp.getId(),sp.getName())).toList();
	}

	@Override
	public List<Student> bestStudentsSubject(int nStudents, String subjectName) {
		return toStudentFromProjList(marksRepository.findBestStudentsSubject(nStudents, subjectName));
	}

	@Override
	public Subject subjectGreatestAvgMark() {
		return toSubjectFromProj(marksRepository.findSubjectGreatestAvgMark());
	}

	@Override
	@Transactional
	public void deleteStudentsAvgMarkLess(int avgMark) {
		//List<StudentEntity> studentsForDelete = studentsRepository.getStudentsAvgMarkLess(avgMark);
		//studentsForDelete.forEach(studentsRepository::delete);
		studentsRepository.deleteStudentsAvgMarkLess((double)avgMark);
	}

	@Override
	public List<String> getStudentsSubjectMark(String subjectName, int mark) {
		return marksRepository.findDistinctBySubjectSubjectNameAndMarkGreaterThanEqual(subjectName,mark).stream().map(StudentNameProjection::getStudentName).toList();
	}

	@Override
	@Transactional
	public List<Student> deleteStudentsMarksCountLess(int count) {
		List<StudentEntity> studentEntitiesForDelete = studentsRepository.getStudentsCountMarkLess(count);
		studentEntitiesForDelete.forEach(studentsRepository::delete);
		return studentEntitiesForDelete.stream().map(e -> new Student(e.getId(),e.getName())).toList();
	}
	@Override
	public List<Subject> subjectsAvgMarkGreater(int avgMark) {
		return marksRepository.findSubjectsAvgMarkGreater((double)avgMark).stream().map(sbp -> new Subject(sbp.getId(),sbp.getName())).toList();
	}

	@Override
	public List<Student> getStudentsAllMarksSubject(int mark, String subject) {
		return toStudentFromProjList(marksRepository.findStudentsAllMarksGreaterEqual(mark, subject));
	}
	
	private List<Student> toStudentFromProjList(List<StudentSubjectProjection> listOfProj){
		return listOfProj.stream().map(sp -> new Student(sp.getId(),sp.getName())).toList();
	}
	private List<Subject> toSubjectFromProjList(List<StudentSubjectProjection> listOfProj){
		return listOfProj.stream().map(sp -> new Subject(sp.getId(),sp.getName())).toList();
	}
	private Subject toSubjectFromProj(StudentSubjectProjection proj){
		return new Subject(proj.getId(), proj.getName());
	}

	@Override
	public List<Student> getStudentsMaxMarksCount() {
		return toStudentFromProjList(marksRepository.findStudentsMaxMarksCount());
	}

	@Override
	public List<Subject> getSubjectsAvgMarkLess(int avgMark) {
		return toSubjectFromProjList(subjectsRepository.findSubjectsAvgMarkLess(avgMark));
	}
}
