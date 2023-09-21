package service;

import java.util.Properties;
import java.util.Scanner;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class SendOTPService2 {
	
	public static void sendOTP2(String email,String genOTP) {
		
		String to=email;
		String from = "michaeltoppo487@gmail.com";
		String subject="File enc - OTP";
		String msg="Your OTP for File Enc App is "+genOTP;
		
		//smtp properties
		Properties properties=new Properties();
		properties.put("mail.smtp.auth",true);
		properties.put("mail.smtp.starttls.enable",true);
		properties.put("mail.smtp.port","587");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		
		Session session=Session.getInstance(properties,new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication("michaeltoppo487@gmail.com","fymmewcoxshjigxc");
			}

			
		});
//		session.setDebug(true);
		try {
			Message message=new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(subject);
			message.setText(msg);
			
			Transport.send(message);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
//	public static void main(String args[]) {
//		System.out.println("Send  random otp");
//		Scanner sc=new Scanner(System.in);
//		String email=sc.nextLine();
//		String genOTP=GenerateOTP.getOPT();
//		System.out.println("OTP generated --"+genOTP);
//		sendOTP2(email,genOTP);
//		System.out.println("Sent succesfully");
//	}

}
