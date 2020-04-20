package com.update.semi.email;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SMTPAuthenticatior extends Authenticator {
	 @Override
	    protected PasswordAuthentication getPasswordAuthentication() {
	        return new PasswordAuthentication("xldksps4@naver.com","rkswnsqjrj24!");
	    }	//"메일","gkssk24**&"

}