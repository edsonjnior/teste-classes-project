package br.com.edsondev.entities;

import br.com.edsondev.enums.TipoArquivo;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "arquivo")
public class Arquivo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Etapa é um campo obrigatório")
    private String etapa;

    @NotNull(message = "Ano é um campo obrigatório")
    private String ano;

    @Enumerated(value = EnumType.STRING)
    private TipoArquivo tipo;

    private String caminho;

    @ManyToOne
    @JoinColumn(name = "teste_id")
    private Teste teste;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getAno() {
	return ano;
    }

    public void setAno(String ano) {
	this.ano = ano;
    }

    public String getCaminho() {
	return caminho;
    }

    public void setCaminho(String caminho) {
	this.caminho = caminho;
    }

    public Teste getTeste() {
	return teste;
    }

    public void setTeste(Teste teste) {
	this.teste = teste;
    }

    public String getEtapa() {
	return etapa;
    }

    public void setEtapa(String etapa) {
	this.etapa = etapa;
    }

    public TipoArquivo getTipo() {
	return tipo;
    }

    public void setTipo(TipoArquivo tipo) {
	this.tipo = tipo;
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
	if (!(object instanceof Arquivo)) {
	    return false;
	}
	Arquivo other = (Arquivo) object;
	if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "Arquivo{" + "id=" + id + ", etapa=" + etapa + ", ano=" + ano + ", tipo=" + tipo + ", caminho=" + caminho
		+ ", teste=" + teste + '}';
    }

}
