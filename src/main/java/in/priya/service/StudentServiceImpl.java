package in.priya.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import in.priya.binding.SearchCriteria;
import in.priya.entity.Student;
import in.priya.repository.StudentEnqRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentEnqRepository studRepo;
	
	@Override
	public boolean saveStud(Student s) {
		 
                      Student stud= studRepo.save(s);
                      
                      
		 if(stud.getSid()!=null)
		 {
			 return true; 
		 }
		 else
		 {
			 return false;
		 }
		
	}

	@Override
	public List<Student> viewStudent(Integer cid , SearchCriteria sc) {
		
	 Student s=new Student();
	 
	 s.setCid(cid);
	 
	 if(sc.getClass_mode()!=null && !sc.getClass_mode().equals(""))
	 {
		 s.setClass_mode(sc.getClass_mode());
	 }
	 if(sc.getCourse()!=null && !sc.getCourse().equals(""))
	 {
		 s.setCourse(sc.getCourse());
	 }
	 
	 if(sc.getStatus()!=null && !sc.getStatus().equals(""))
	 {
		 s.setStatus(sc.getStatus());
	 }
	 Example<Student> of= Example.of(s);
	 
	 
		return studRepo.findAll(of);
		
	
	}
	
	
	
	

}
