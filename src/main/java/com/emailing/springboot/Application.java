package com.emailing.springboot;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;
import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;

import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;


//import com.emailing..EmailSenderService;
//import com.dailycodebuffer.springemailclient.SpringEmailClientApplication;

import javax.mail.MessagingException;


@SpringBootApplication
public class Application {

	@Autowired
	private EmailSenderService service;

	
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

   
		@EventListener(ApplicationReadyEvent.class)
		public void triggerMail() throws MessagingException {
			service.sendEmailWithAttachment("kaceee035@gmail.com",
										"This email has attachment",
					"C:\\Users\\Lenovo\\Downloads\\pa2\\smartphone.jpg");
	
}
}

