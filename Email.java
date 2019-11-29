import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class Email {

		  String to="";     // Recipient's email ID .
		  String name=" ";
		  String phone=" ";
		  String email=" ";
		  String hname=" ";
		  String checkin=" ";
		  String checkout=" ";
		  String address=" ";
		  
		  
		  
		  Email(String to , String name, String phone, String email)//Email Contructor For initialising Visitor details to Send Visitor Details to Host.
		  {
			  this.to=to;
			  this.name=name;
			  this.phone=phone;
			  this.email=email;
		  }
		  
		  Email(String to, String name, String phone, String checkin, String checkout,String hname,String address)//Email Contructor For initialising Visit details to Send Visit Details to Visitor after Checkout.
		  {
			  this.to=to;
			  this.name=name;
			  this.phone=phone;
			  this.checkout=checkout;
			  this.checkin=checkin;
			  this.hname=hname;
			  this.address=address;
		  }

      String from = "tempsar2019@gmail.com";// Common Mail which Sends Mails to both Visitor and Host

      String host = "smtp.gmail.com";
      
      String pass="abc@12315";// Password of the Common Mail
      
      void sendtohost()//function which sends Mail to Host
      {

      // Get system properties
      Properties properties = System.getProperties();
      
      properties.put("mail.smtp.port", "587");
      
      properties.put("mail.smtp.auth","true");
      
      properties.put("mail.smtp.starttls.enable","true");

      // Setup mail server
      properties.setProperty("mail.smtp.host", host);
      

      // Making Session Object 
      Session session = Session.getInstance(properties,new Authenticator() {
    	protected PasswordAuthentication getPasswordAuthentication()
    	{
    		return new PasswordAuthentication(from,pass);
    	}
      });

      try {
         // Create a default MimeMessage object.
         MimeMessage message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));

         // Set To: header field of the header.
         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

         // Set Subject: header field
         message.setSubject("Vistor Details.");

         // Now set the actual message
         message.setText("Name of Host :"+name+"\n"+"Phone number of Host :"+phone+"\n"+"Email of Host :"+email+"\n");

         // Send message
         Transport.send(message);
         System.out.println("Sent message to Host successfully....");
      } catch (MessagingException mex) {
         mex.printStackTrace();
      }
      }
      
      void sendtoVisitor()//function which sends mail to Visitor
      {

      // Get system properties
      Properties properties = System.getProperties();
      
      properties.put("mail.smtp.port", "587");
      
      properties.put("mail.smtp.auth","true");
      
      properties.put("mail.smtp.starttls.enable","true");

      // Setup mail server
      properties.setProperty("mail.smtp.host", host);
      

      // Making Session object.
      Session session = Session.getInstance(properties,new Authenticator() {
    	protected PasswordAuthentication getPasswordAuthentication()
    	{
    		return new PasswordAuthentication(from,pass);
    	}
      });

      try {
         // Create a default MimeMessage object.
         MimeMessage message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));

         // Set To: header field of the header.
         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

         // Set Subject: header field
         message.setSubject("Vist Details.");

         // Now set the actual message
         message.setText("Name of Visitor :"+name+"\n"+"Phone number of Visitor :"+phone+"\n"+"Checkin Time: "+checkin+"\n"+"Checkout Time: "+checkout+"\n"+"Host name: "+hname+"\n"+"Address: "+address+"\n");

         // Send message
         Transport.send(message);
         System.out.println("Sent message to Visitor successfully....");
      } catch (MessagingException mex) {
         mex.printStackTrace();
      }
   }
}
