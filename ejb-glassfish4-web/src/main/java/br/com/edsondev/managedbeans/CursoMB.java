package br.com.edsondev.managedbeans;

import br.com.edsondev.entities.Curso;
import br.com.edsondev.repos.CursoFacade;
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
public class CursoMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(CursoMB.class.getName());

    @EJB
    private CursoFacade cursoFacade;
    private Curso curso;
    private List<Curso> cursos;

    private String messageSeverity;
    private String cursoId;

    public CursoMB() {
	curso = new Curso();

	// classe css para customização do painel de mensagens
	messageSeverity = "primary";

	// Recebendo o id do curso como parâmetro para edição
	FacesContext fc = FacesContext.getCurrentInstance();
	Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
	cursoId = params.get("cursoId");
    }

    @PostConstruct
    void init() {
	if (cursoId != null && !"".equals(cursoId)) {
	    curso = cursoFacade.find(new Long(cursoId));
	}
    }

    public String salvarCurso() {
	try {
	    // // Teste para invalidar um campo
	    // if (curso.getDescricao().equalsIgnoreCase("teste")) {
	    // FacesContext fc = FacesContext.getCurrentInstance();
	    //
	    // UIInput descricaoInput = (UIInput)
	    // fc.getViewRoot().findComponent("formCadastroCurso:descricao");
	    // if (descricaoInput != null) {
	    // descricaoInput.setValid(false);
	    //
	    // fc.addMessage("descricao", new FacesMessage("Esta descrição é
	    // inválida"));
	    // }
	    // return null;
	    // }
	    if (curso.getId() != null) {
		cursoFacade.edit(curso);
	    } else {
		cursoFacade.create(curso);
	    }

	    curso = new Curso();

	    return "cursos?faces-redirect=true";
	} catch (Exception e) {
	    LOG.log(Level.SEVERE, e.getMessage());
	}

	return null;
    }

    public void excluirCurso() {
	try {
	    cursos.remove(curso);
	    cursoFacade.remove(curso);

	    messageSeverity = "success";

	    FacesContext fc = FacesContext.getCurrentInstance();
	    fc.addMessage(null, new FacesMessage("Curso excluído com sucesso."));
	} catch (Exception e) {
	    LOG.log(Level.SEVERE, e.getMessage());
	}
    }

    public Curso getCurso() {
	return curso;
    }

    public void setCurso(Curso curso) {
	this.curso = curso;
    }

    public List<Curso> getCursos() {
	if (cursos == null) {
	    cursos = cursoFacade.findAll();
	}
	return cursos;
    }

    public void setCursos(List<Curso> cursos) {
	this.cursos = cursos;
    }

    public String getMessageSeverity() {
	return messageSeverity;
    }

    public void setMessageSeverity(String messageSeverity) {
	this.messageSeverity = messageSeverity;
    }

    public String getCursoId() {
	return cursoId;
    }

    public void setCursoId(String cursoId) {
	this.cursoId = cursoId;
    }

}
