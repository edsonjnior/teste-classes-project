package br.com.edsondev.managedbeans;

import br.com.edsondev.entities.Curso;
import br.com.edsondev.enums.Segmento;
import br.com.edsondev.repos.CursoFacade;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class TesteMB implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private CursoFacade cursoFacade;

    private Curso curso;

    public TesteMB() {
    }

    @PostConstruct
    void init() {
    }

    public int getNumeroDeContatos() {
//        return contatoFacade.findAll().size();
        return 0;
    }

    public List<Segmento> getSegmentos() {
        return Arrays.asList(Segmento.values());
    }

    public String salvarCurso() {

        return null;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

}
