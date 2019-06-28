package br.com.edsondev.entities;

import br.com.edsondev.enums.Segmento;
import br.com.edsondev.enums.Status;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author junior
 */
@Entity
@Table(name = "teste")
public class Teste implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Titulo é um campo obrigatório")
    private String titulo;

    @NotNull(message = "Data do evento é um campo obrigatório")
    @Column(name = "data_evento", nullable = false)
    private LocalDate dataEvento;

    @NotNull(message = "Data de início do evento é um campo obrigatório")
    @Column(name = "data_inicio_evento", nullable = false)
    private LocalTime dataInicioEvento;

    @NotNull(message = "Data de término do evento é um campo obrigatório")
    @Column(name = "data_termino_evento", nullable = false)
    private LocalTime dataTerminoEvento;

    @NotNull(message = "Data de início da inscrição é um campo obrigatório")
    @Column(name = "data_inicio_inscricao", nullable = false)
    private LocalDateTime dataInicioInscricao;

    @NotNull(message = "Data de término da inscrição é um campo obrigatório")
    @Column(name = "data_termino_inscricao", nullable = false)
    private LocalDateTime dataTerminoInscricao;

    @NotNull(message = "Segmento é um campo obrigatório")
    @Enumerated(EnumType.STRING)
    private Segmento segmento;

    @NotNull(message = "Status é um campo obrigatório")
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "teste")
    private List<Arquivo> arquivos = new ArrayList<>();

    @ElementCollection
    @Column(name = "texto")
    @CollectionTable(name = "teste_texto", joinColumns = @JoinColumn(name = "teste_id", referencedColumnName = "id"))
    private Set<String> textos = new HashSet<>();

    @NotNull(message = "Inclua alguma informação sobre o evento")
    @Column(name = "info_evento", nullable = false)
    private String informacaoEvento;

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Aluno.class)
    @JoinTable(name = "aluno_teste", joinColumns = @JoinColumn(name = "teste_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "aluno_id", referencedColumnName = "id"))
    private List<Aluno> alunos;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public LocalDate getDataEvento() {
	return dataEvento;
    }

    public void setDataEvento(LocalDate dataEvento) {
	this.dataEvento = dataEvento;
    }

    public LocalTime getDataInicioEvento() {
	return dataInicioEvento;
    }

    public void setDataInicioEvento(LocalTime dataInicioEvento) {
	this.dataInicioEvento = dataInicioEvento;
    }

    public LocalTime getDataTerminoEvento() {
	return dataTerminoEvento;
    }

    public void setDataTerminoEvento(LocalTime dataTerminoEvento) {
	this.dataTerminoEvento = dataTerminoEvento;
    }

    public LocalDateTime getDataInicioInscricao() {
	return dataInicioInscricao;
    }

    public void setDataInicioInscricao(LocalDateTime dataInicioInscricao) {
	this.dataInicioInscricao = dataInicioInscricao;
    }

    public LocalDateTime getDataTerminoInscricao() {
	return dataTerminoInscricao;
    }

    public void setDataTerminoInscricao(LocalDateTime dataTerminoInscricao) {
	this.dataTerminoInscricao = dataTerminoInscricao;
    }

    public Segmento getSegmento() {
	return segmento;
    }

    public void setSegmento(Segmento segmento) {
	this.segmento = segmento;
    }

    public Status getStatus() {
	return status;
    }

    public void setStatus(Status status) {
	this.status = status;
    }

    public String getInformacaoEvento() {
	return informacaoEvento;
    }

    public void setInformacaoEvento(String informacaoEvento) {
	this.informacaoEvento = informacaoEvento;
    }

    public List<Arquivo> getArquivos() {
	return arquivos;
    }

    public void setArquivos(List<Arquivo> arquivos) {
	this.arquivos = arquivos;
    }

    public Set<String> getTextos() {
	return textos;
    }

    public void setTextos(Set<String> textos) {
	this.textos = textos;
    }

    public String getTitulo() {
	return titulo;
    }

    public void setTitulo(String titulo) {
	this.titulo = titulo;
    }

    public List<Aluno> getAlunos() {
	return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
	this.alunos = alunos;
    }

    @Override
    public int hashCode() {
	int hash = 0;
	hash += (id != null ? id.hashCode() : 0);
	return hash;
    }

    @Override
    public boolean equals(Object object) {
	// TODO: Warning - this method won't work in the case the id fields are
	// not set
	if (!(object instanceof Teste)) {
	    return false;
	}
	Teste other = (Teste) object;
	if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "br.com.edsondev.entities.Teste[ id=" + id + " ]";
    }

}
