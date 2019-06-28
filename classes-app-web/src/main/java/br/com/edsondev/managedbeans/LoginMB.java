package br.com.edsondev.managedbeans;

import br.com.edsondev.email.MailSenderBean;
import br.com.edsondev.entities.Aluno;
import br.com.edsondev.repository.AlunoFacade;
import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.mail.MessagingException;

@Named
@ViewScoped
public class LoginMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(LoginMB.class.getName());

    @EJB
    private AlunoFacade alunoFacade;
    @EJB
    private MailSenderBean mailSender;

    private String messageSeverity = "danger";
    private String emailRecuperacao;

    public LoginMB() {
    }

    @PostConstruct
    void init() {
    }

    public void lembrarSenha(String feedbackId) {
	FacesMessage message;

	FacesContext context = FacesContext.getCurrentInstance();

	Aluno aluno = alunoFacade.findByEmail(emailRecuperacao);
	String msg = aluno == null ? "Desculpe, mas não fomos capazes de encontrar o email informado."
		: "Enviamos um email para " + aluno.getEmail() + " com as instruções de redefinição de senha.";
	messageSeverity = aluno == null ? "danger" : "success";

	message = new FacesMessage(msg);
	if (feedbackId != null && !"".equals(feedbackId)) {

	}

	if (aluno != null) {
	    try {
		Properties mailProps = new Properties();
		mailProps.load(getClass().getResourceAsStream("/mail.properties"));
		mailSender.enviarEmail("no-reply@classes.com.br", aluno.getEmail(), "Recuperação de senha", "Teste",
			mailProps);
	    } catch (IOException ex) {
		messageSeverity = "danger";
		message = new FacesMessage("Ocorreu um erro ao tentar enviar o email.");

		LOG.log(Level.SEVERE, null, ex);
	    }
	}

	context.addMessage(null, message);
    }

    public String getMessageSeverity() {
	return messageSeverity;
    }

    public void setMessageSeverity(String messageSeverity) {
	this.messageSeverity = messageSeverity;
    }

    public String getEmailRecuperacao() {
	return emailRecuperacao;
    }

    public void setEmailRecuperacao(String emailRecuperacao) {
	this.emailRecuperacao = emailRecuperacao;
    }

}
