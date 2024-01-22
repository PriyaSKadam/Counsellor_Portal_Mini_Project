package in.priya.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.priya.binding.Dashboard;
import in.priya.entity.Counsellor;
import in.priya.entity.Student;
import in.priya.repository.CounsellorRepository;
import in.priya.repository.StudentEnqRepository;
import in.priya.util.MailUtility;

@Service
public class CounsellorServiceImpl implements CounsellorService {
	
	

	@Autowired
	private CounsellorRepository crepo;
	
	@Autowired
	private StudentEnqRepository srepo;
	
	
	@Autowired
	
	private MailUtility mailUtil;

	@Override
	public boolean saveCounsellor(Counsellor c) {
		
		 boolean status=crepo.existsByEmail(c.getEmail());
		 
		 if(status)
		 {
			 return false;
		 }
		 else
		 {
			 crepo.save(c);
			 return true;
		 }
		
	}
	
	@Override
	public Counsellor handleLogin(String email, String password) {
		
		    return crepo.findByEmailAndPassword(email, password);
	
	}

	@Override
	public boolean handlePassword(String email) {
		
		 Counsellor c=crepo.findByEmail(email);
		 if(c!=null)
		 {
			 String subject="Recover Password - Priya IT";
				String body="<h3>Your password is : "+c.getPassword()+"</h3>";
				
				boolean status=mailUtil.mailSend(subject, body,email);
				
				return status;
		 }
		       
		       else
		       {
		    	   return false;
		       }
		             
		
	}

	@Override
	public Dashboard getDashboard(Integer cid) {
	    
		   Dashboard d=new Dashboard();
		   
		   List<Student> allEnq =srepo.findByCid(cid);
		   
		   Integer enrolled=allEnq.stream().filter(e->e.getStatus().equals("Enrolled")).collect(Collectors.toList()).size();
		   
		   Integer lost=allEnq.stream().filter(e->e.getStatus().equals("Lost")).collect(Collectors.toList()).size();
		   
		   d.setTotal(allEnq.size());
		   d.setEnrolled(enrolled);
		   d.setLosted(lost);
		   
		 
		return  d;
	}
	
	
	
	


	
	
	

}
