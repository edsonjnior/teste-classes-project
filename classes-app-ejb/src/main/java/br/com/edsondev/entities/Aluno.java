package br.com.edsondev.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Table(name = "aluno")
public class Aluno implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "Nome é um campo obrigatório")
    private String nome;

    @Column(nullable = false, unique = true)
    @NotNull(message = "Email é um campo obrigatório")
    @Email(message = "Email deve ser válido")
    private String email;

    @Column(nullable = false, unique = true)
    @CPF(message = "CPF deve ser válido")
    @NotNull(message = "CPF é um campo obrigatório")
    private String cpf;

    @Column(name = "data_nascimento", nullable = false)
    @NotNull(message = "Data de nascimento é um campo obrigatório")
    @Past(message = "Data de nascimento deve ser válida")
    private LocalDate dataNascimento;

    @Column(nullable = false)
    @NotNull(message = "Senha é um campo obrigatório")
    @Size(min = 8, max = 30, message = "Senha deve conter de 8 a 30 caracteres")
    private String senha;

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Teste.class)
    @JoinTable(name = "aluno_teste", joinColumns = @JoinColumn(name = "aluno_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "teste_id", referencedColumnName = "id"))
    private List<Teste> testes;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getNome() {
	return nome;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getCpf() {
	return cpf;
    }

    public void setCpf(String cpf) {
	this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
	return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
	this.dataNascimento = dataNascimento;
    }

    public String getSenha() {
	return senha;
    }

    public void setSenha(String senha) {
	this.senha = senha;
    }

    public List<Teste> getTestes() {
	return testes;
    }

    public void setTestes(List<Teste> testes) {
	this.testes = testes;
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
	if (!(object instanceof Aluno)) {
	    return false;
	}
	Aluno other = (Aluno) object;
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
