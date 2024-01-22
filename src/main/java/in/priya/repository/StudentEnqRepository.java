package in.priya.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import in.priya.entity.Student;

public interface StudentEnqRepository extends JpaRepository<Student, Integer>{
	
	
	public List<Student> findByCid(Integer cid);

}
