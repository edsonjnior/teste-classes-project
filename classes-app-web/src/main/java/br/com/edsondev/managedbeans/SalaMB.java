package br.com.edsondev.managedbeans;

import br.com.edsondev.entities.Sala;
import br.com.edsondev.repository.SalaFacade;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class SalaMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(SalaMB.class.getName());

    @EJB
    private SalaFacade salaFacade;
    private List<Sala> salas;

    private String messageSeverity;
    private String salaId;
    private Sala sala;

    public SalaMB() {
	novoSala();

	// classe css para customização do painel de mensagens
	messageSeverity = "primary";

	// Recebendo o id da sala como parâmetro para edição
	FacesContext fc = FacesContext.getCurrentInstance();
	Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
	salaId = params.get("salaId");
    }

    @PostConstruct
    void init() {
	if (salaId != null && !"".equals(salaId)) {
	    sala = salaFacade.find(new Long(salaId));
	}
    }

    public void novoSala() {
	sala = new Sala();
    }

    public String salvarSala() {
	try {
	    if (sala.getId() != null) {
		salaFacade.edit(sala);
	    } else {
		salaFacade.create(sala);
	    }

	    novoSala();

	    return "salas?faces-redirect=true";
	} catch (Exception e) {
	    LOG.log(Level.SEVERE, e.getMessage());
	}

	return null;
    }

    public void excluirSala() {
	try {
	    salas.remove(sala);
	    salaFacade.remove(sala);

	    messageSeverity = "success";

	    FacesContext fc = FacesContext.getCurrentInstance();
	    fc.addMessage(null, new FacesMessage("Sala excluída com sucesso."));
	} catch (Exception e) {
	    LOG.log(Level.SEVERE, e.getMessage());
	}
    }

    public Sala getSala() {
	return sala;
    }

    public void setSala(Sala sala) {
	this.sala = sala;
    }

    public List<Sala> getSalas() {
	if (salas == null) {
	    salas = salaFacade.findAll();
	}
	return salas;
    }

    public void setSalas(List<Sala> salas) {
	this.salas = salas;
    }

    public String getMessageSeverity() {
	return messageSeverity;
    }

    public void setMessageSeverity(String messageSeverity) {
	this.messageSeverity = messageSeverity;
    }

    public String getCursoId() {
	return salaId;
    }

    public void setCursoId(String salaId) {
	this.salaId = salaId;
    }
}
