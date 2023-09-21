//package service;
//
//import java.util.Properties;
//import java.util.Scanner;
//
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//import javax.mail.Authenticator;
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.PasswordAuthentication;
//
//public class SendOTPService {
//	
//	
//	public static void sendOTP(String email,String genOTP) {
//		
//		//Recipient's email ID
//		String to=email;
//		
//		//Sender's email ID
//		String from = "michaeltoppo487@gmail.com";
//		
//		//Assuming you are sending through gmail smtp
//		String host = "smtp.gmail.com";
//		
//		//Get system properties
//		Properties properties=System.getProperties();
//		
//		//Setup mail server
//		properties.put("mail.smtp.host", host);
//		properties.put("mail.smtp.port","465");
//		properties.put("mail.smtp.ssl.enable","true");
//		properties.put("mail.smtp.auth", "true");
//		
//		//Get the Session object.// and pass username and password
//		Session session=Session.getInstance(properties,new Authenticator() {
//
//			@Override
//			protected PasswordAuthentication getPasswordAuthentication() {
//				// TODO Auto-generated method stub
//				return new PasswordAuthentication("michaeltoppo487@gmail.com","fymmewcoxshjigxc");
//			}
//			
//		});
//
//		
////		Session session=Session.getDefaultInstance(from, getPasswordAuthentication(from)->{
////			return new PasswordAuthentication(from,"fymmewcoxshjigxc");
////		});
//		
//		//Used to debug SMTP issues
//		session.setDebug(true);
//		try {
//			//Create a default MinmeMessage object
//			MimeMessage message=new MimeMessage(session);
//			
//			//Set from : header field of the header
//			message.setFrom(new InternetAddress(from));
//			
//			//Set To:header field of the header
//			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//			
//			//Set Subject:header field
//			message.setSubject("File enc - OTP");
//			
//			//Now set the actual message
//			message.setText("Your OTP for File Enc App is"+genOTP);
//			System.out.println("sending...");
//			Transport.send(message);
//			System.out.println("Sent message successfully");
//		}catch(MessagingException mex) {
//			mex.printStackTrace();
//		}
//	}
//	
////	public static void main(String args[]) {
////		System.out.println("Send  random otp");
////		Scanner sc=new Scanner(System.in);
////		String email=sc.nextLine();
////		String genOTP=GenerateOTP.getOPT();
////		System.out.println("OTP generated --"+genOTP);
////		sendOTP(email,genOTP);
////		System.out.println("Sent succesfully");
////	}
//
//}
