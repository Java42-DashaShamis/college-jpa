package telran.college.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.college.entities.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

}
