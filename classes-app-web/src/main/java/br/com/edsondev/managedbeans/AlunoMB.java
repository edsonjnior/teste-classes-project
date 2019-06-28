package br.com.edsondev.managedbeans;

import br.com.edsondev.entities.Aluno;
import br.com.edsondev.repository.AlunoFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class AlunoMB implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private AlunoFacade alunoFacade;

    private Aluno aluno;

    public AlunoMB() {
    }

    @PostConstruct
    void init() {
    }

    public void novoAluno() {
	aluno = new Aluno();
    }

    public String salvarAluno() {

	return null;
    }

    public Aluno getAluno() {
	return aluno;
    }

    public void setAluno(Aluno aluno) {
	this.aluno = aluno;
    }

}
