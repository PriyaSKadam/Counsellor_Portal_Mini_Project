package in.priya.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.priya.binding.SearchCriteria;
import in.priya.entity.Student;
import in.priya.service.StudentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class StudentController {
	
	@Autowired
	private StudentService  studService;
	
	@GetMapping("/addEnq")
	public String addStudentEnquiry(Model model)
	{
		model.addAttribute("s", new Student());
		
		return "addEnquiry";
	}
	
	@PostMapping("/saveStudent")
	public String saveStudent(@ModelAttribute("s") Student s,HttpServletRequest req,Model model)
	{
		HttpSession session=req.getSession(false);
		
		Integer cid=(Integer)session.getAttribute("CID");
		
		if(cid==null)
		{
			return "redirect:/logout";
		}
		
		s.setCid(cid);
	       boolean status=studService.saveStud(s);
	       if(status)
	       {
	    	   model.addAttribute("smsg","Enquiry added successfully...");
	       }
	       else
	       {
	    	   model.addAttribute("fmsg","Enquiry Failed To Add");
	       }
		
		return "addEnquiry";
	}
	
	@GetMapping("/allEnq")
	
	public String allEnquiries(Model model,HttpServletRequest req)
	{
		HttpSession session=req.getSession(false);
		Integer cid=(Integer)session.getAttribute("CID");
		
		if(cid==null)
		{
			return "redirect:logout";
		}
		
		     model.addAttribute("sc",new SearchCriteria());
		
		    List<Student> list=studService.viewStudent(cid,new SearchCriteria());
		    
		    model.addAttribute("enquiries", list);
		
		return "viewEnquiries";
	}
	
	@PostMapping("/filter-enquiries")
	public String filterEnquiries(@ModelAttribute("sc") SearchCriteria sc, HttpServletRequest req, Model model) {

		HttpSession session = req.getSession(false);
		Integer cid = (Integer) session.getAttribute("CID");

		if (cid == null) {
			return "redirect:/logout";
		}

		List<Student> enquiriesList = studService.viewStudent(cid, sc);

		model.addAttribute("enquiries", enquiriesList);

		return "filterEnqTable";
	}

	
	
	

}
