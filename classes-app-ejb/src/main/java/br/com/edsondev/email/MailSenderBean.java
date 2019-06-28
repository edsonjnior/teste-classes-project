/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.edsondev.email;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sun.mail.smtp.SMTPTransport;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author junior
 */
@Stateless
public class MailSenderBean {

    private static final Logger LOG = Logger.getLogger(MailSenderBean.class.getName());

    /**
     * Envio de email através do Sendgrid (test)
     * 
     * @param fromEmail
     *            de
     * @param toEmail
     *            para
     * @param subject
     *            assunto
     * @param message
     *            texto da mensagem
     * @param mailProps
     *            properties
     * @throws IOException
     */
    @Asynchronous
    public void enviarEmail(String fromEmail, String toEmail, String subject, String message, Properties mailProps)
	    throws IOException {

	Email from = new Email(fromEmail);
	Email to = new Email(toEmail);
	Content content = new Content("text/plain", message);
	Mail mail = new Mail(from, subject, to, content);

	SendGrid sg = new SendGrid(mailProps.getProperty("api.key"));
	Request request = new Request();
	try {
	    request.setMethod(Method.POST);
	    request.setEndpoint("mail/send");
	    request.setBody(mail.build());
	    Response response = sg.api(request);
	} catch (IOException ex) {
	    throw ex;
	}
    }
}
