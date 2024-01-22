package in.priya.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.priya.binding.Dashboard;
import in.priya.entity.Counsellor;
import in.priya.service.CounsellorService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CounsellorController {
	
	
	@Autowired
	private CounsellorService cservice;
	
	@GetMapping("/reg")
	public String registrationPage(Model model)
	{
		model.addAttribute("registration",new Counsellor());
		
		return "registration";
	}
	
	@PostMapping("/save")
	public String saveCounsellor(Counsellor c, Model model)
	{
		boolean status=cservice.saveCounsellor(c); 
		
		if(status)
		{
			model.addAttribute("smsg", "Account created successfully..Please check with login");

		}
		else
		{
			model.addAttribute("fmsg", "This mail id is already registered");
		}
		
		return "registration";
	}

	
	
	@GetMapping("/index")
		public String loginPage(Model model)
	{
		model.addAttribute("login", new Counsellor());
		
		return "login";
	}
	
	@PostMapping("/login")
	
	public String loginCheck(Model model, HttpServletRequest resq, Counsellor c)
	{
		       Counsellor obj=cservice.handleLogin(c.getEmail(),c.getPassword());
		       
		       if(obj!=null)
		       {
		    	   HttpSession session=resq.getSession(true);
		    	   
		    	   session.setAttribute("CID", obj.getCid());
		    	   return "redirect:dashboard";
		       }
		       else
		       {
		    	   model.addAttribute("msg","Invalid Credential");
		    	   return "login";
		       }
		
	}
	
	@GetMapping("/logout")
	public String logoutFunction(HttpServletRequest req,Model model)
	{
		HttpSession session=req.getSession(false);
		session.invalidate();
		
		return "redirect:/index";
	}
	
	@GetMapping("/dashboard")
    public String showDashboard(HttpServletRequest req,Model model)
    {
		
		HttpSession session=req.getSession(false);
		
		Object obj=session.getAttribute("CID");
		
		Integer cid=(Integer)obj;
		
		  Dashboard d= cservice.getDashboard(cid);
		   model.addAttribute("dashboard", d);
		return "dashboard";
    }
	
	
	@GetMapping("/fpass")
	public String forgotPwd(Model model)
	{
		model.addAttribute("forgot", new Counsellor());
		
		return "forgotPwd";
	}
	
	@PostMapping("/getpassword")
	public String handleForgPwd(@RequestParam String email, Model model)
	{
		boolean status=cservice.handlePassword(email);
		
		
		if(status)
		{
			model.addAttribute("smsg", "mail sent on your mail");
		}
		else
		{
			model.addAttribute("fmsg","Invalid Message");
		}
		
		
		
		return "forgotPwd";
	}
	
	
	
	
	@GetMapping("/contactus")
	
	public String getContactUsPage(Model model)
	{
	   return "contactus";
	}
	
	@GetMapping("/courses")
	public String getCorsesPage(Model model)
	{
		return "courses";
	}
	
	@GetMapping("/service")
	
	public String getServicePage(Model model)
	{
		return "service";
	}
}
