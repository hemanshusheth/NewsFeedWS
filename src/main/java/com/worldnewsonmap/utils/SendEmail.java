package com.worldnewsonmap.utils;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SendEmail
 */
@WebServlet(name= "SendEmail" ,urlPatterns = {"/sendMessageServlet"})
public class SendEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendEmail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String location = request.getParameter("location");
		String message= request.getParameter("message");
		SendMessage(name, location, message);
		response.setContentType("text/html"); 
	    // output a message that notifies the user that the browser is
	    // going to be redirected to a new page
	    response.getWriter().append("<HTML>")
						     .append("<BODY>")
						     .append("Go back from browser<BR>")
						     .append("</BODY>")
						     .append("</HTML>");
	}

	/*
	 * 
	 */
	public void SendMessage(String name, String location, String msg) {
		Properties properties = new Properties();
		properties.put("mail.smtp.starttls.enable","true");
		properties.put("mail.smtp.host", "xjdz3.dailyrazor.com");
		properties.put("mail.smtp.socketFactory.port", "465");
		properties.put("mail.smtp.socketFactory.class",
				  	   "javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.port", "465");

	    // Get the default Session object.
		Session session = Session.getDefaultInstance(properties,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("contactus@googlenewsonmap.com",
																		"Feedback#007");
				}
			});

		// Recipient's email ID needs to be mentioned.
	      String to = "contactus@googlenewsonmap.com";

	      // Sender's email ID needs to be mentioned
	      String from = "contactus@googlenewsonmap.com";

	      try{
	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

	         // Set Subject: header field
	         message.setSubject(name +" from " +location
	         						 + " has a Feedback for GoogleMapNews.com!");

	         // Now set the actual message
	         message.setText(msg);

	         // Send message
	         Transport.send(message);
	         System.out.println("Sent message successfully....");
	      }
	      catch (MessagingException mex){
	    	  ServiceLogger.Severe(mex.getMessage());
	      }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
