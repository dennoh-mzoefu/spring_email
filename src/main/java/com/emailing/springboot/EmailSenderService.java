package com.emailing.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;



import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;
    
    @Autowired
	private Configuration config;
	

    public void sendSimpleEmail(String toEmail,
                                String html,
                                String subject) {
        SimpleMailMessage message = new SimpleMailMessage();
        
        message.setFrom("kaceee035@gmail.com");
        message.setTo(toEmail);
        message.setText(html);
        message.setSubject(subject);

        mailSender.send(message);
        System.out.println("Mail Send...");
    }
   
       public  Object sendEmail() {
   		 Map<String, Object> model = new HashMap<>();
   			model.put("Name", "Denis ");
   			model.put("location", "Nairobi,Kenya");
   			return model;
   		}
       
    public void sendEmailWithAttachment(String toEmail,
                                        
                                        String subject,
                                        String attachment) throws MessagingException {
    	try {
			
			Template t = config.getTemplate("email-template.ftl");
			String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, sendEmail());
			MimeMessage mimeMessage = mailSender.createMimeMessage();
	        
//	        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
//					StandardCharsets.UTF_8.name());
	        MimeMessageHelper mimeMessageHelper
	                = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
	     				StandardCharsets.UTF_8.name());

	        mimeMessageHelper.setFrom("kaceee035@gmail.com");
	        mimeMessageHelper.setTo(toEmail);
	        mimeMessageHelper.setText(html,true);
	        mimeMessageHelper.setSubject(subject);

	        FileSystemResource fileSystem
	                = new FileSystemResource(new File(attachment));

	        mimeMessageHelper.addAttachment(fileSystem.getFilename(),
	                fileSystem);

	        
	        mailSender.send(mimeMessage);
	        System.out.println("Mail Send...");
				
	
}


 catch(TemplateException e) {
 	System.out.println("Mail Sending failure : "+e.getMessage());
} catch (TemplateNotFoundException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (MalformedTemplateNameException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (ParseException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}     	

        
        }
       
}

