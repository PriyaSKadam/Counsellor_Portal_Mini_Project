package in.priya.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.priya.entity.Counsellor;

public interface CounsellorRepository extends JpaRepository<Counsellor, Integer> {
	
	public boolean existsByEmail(String email);
	
	public Counsellor findByEmailAndPassword(String email,String password);
	
	public Counsellor findByEmail(String email);

}
