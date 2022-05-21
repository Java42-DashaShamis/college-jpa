package telran.college.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.college.entities.SubjectEntity;

public interface SubjectRepository extends JpaRepository<SubjectEntity, Long> {

}
