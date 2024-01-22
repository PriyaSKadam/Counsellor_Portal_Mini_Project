package in.priya.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;

@Component
public class MailUtility {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public boolean mailSend(String subject,String body, String to)
	{
		boolean isSent=false;
		try {
			
		
		MimeMessage mimeMessage=mailSender.createMimeMessage();
		
		MimeMessageHelper helper=new MimeMessageHelper(mimeMessage);
		
		helper.setTo(to);
		
		helper.setSubject(subject);
		
		helper.setText(body,true);
		
		mailSender.send(mimeMessage);
		
		isSent=true;
		}
		catch(Exception e)
		{
		   
		}
		
		return isSent;
	}
	

}
