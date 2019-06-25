package br.com.edsondev.managedbeans;

import br.com.edsondev.entities.InformacaoExtra;
import br.com.edsondev.repository.InformacaoExtraFacade;
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
public class TextosMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(TextosMB.class.getName());

    @EJB
    private InformacaoExtraFacade informacaoExtraFacade;
    private List<InformacaoExtra> textos;

    private String messageSeverity;
    private String textoId;
    private InformacaoExtra texto;

    public TextosMB() {
	novoTexto();

	// classe css para customização do painel de mensagens
	messageSeverity = "primary";

	// Recebendo o id da texto como parâmetro para edição
	FacesContext fc = FacesContext.getCurrentInstance();
	Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
	textoId = params.get("textoId");
    }

    @PostConstruct
    void init() {
	if (textoId != null && !"".equals(textoId)) {
	    texto = informacaoExtraFacade.find(new Long(textoId));
	}
    }

    public void novoTexto() {
	texto = new InformacaoExtra();
    }

    public String salvarTexto() {
	try {
	    if (texto.getId() != null) {
		informacaoExtraFacade.edit(texto);
	    } else {
		informacaoExtraFacade.create(texto);
	    }

	    novoTexto();

	    return "textos?faces-redirect=true";
	} catch (Exception e) {
	    LOG.log(Level.SEVERE, e.getMessage());
	}

	return null;
    }

    public void excluirTexto() {
	try {
	    textos.remove(texto);
	    informacaoExtraFacade.remove(texto);

	    messageSeverity = "success";

	    FacesContext fc = FacesContext.getCurrentInstance();
	    fc.addMessage(null, new FacesMessage("Texto excluído com sucesso."));
	} catch (Exception e) {
	    LOG.log(Level.SEVERE, e.getMessage());
	}
    }

    public InformacaoExtra getTexto() {
	return texto;
    }

    public void setTexto(InformacaoExtra texto) {
	this.texto = texto;
    }

    public List<InformacaoExtra> getTextos() {
	if (textos == null) {
	    textos = informacaoExtraFacade.findAll();
	}
	return textos;
    }

    public void setTextos(List<InformacaoExtra> textos) {
	this.textos = textos;
    }

    public String getMessageSeverity() {
	return messageSeverity;
    }

    public void setMessageSeverity(String messageSeverity) {
	this.messageSeverity = messageSeverity;
    }

    public String getCursoId() {
	return textoId;
    }

    public void setCursoId(String textoId) {
	this.textoId = textoId;
    }
}
