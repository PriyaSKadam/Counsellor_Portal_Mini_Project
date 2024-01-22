package in.priya.service;
import java.util.List;

import in.priya.binding.SearchCriteria;
import in.priya.entity.Student;

public interface StudentService {
	
	public boolean saveStud(Student s);
	
	public List<Student> viewStudent(Integer cid,SearchCriteria sc);
	

}
