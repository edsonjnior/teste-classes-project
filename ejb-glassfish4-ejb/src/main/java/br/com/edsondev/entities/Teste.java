/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.edsondev.entities;

import br.com.edsondev.enums.Segmento;
import br.com.edsondev.enums.Status;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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

    @NotNull(message = "Data do evento é um campo obrigatório")
    @Column(name = "data_evento", nullable = false)
    private LocalDate dataEvento;

    @NotNull(message = "Data de início do evento é um campo obrigatório")
    @Column(name = "data_inicio_evento", nullable = false)
    private LocalDateTime dataInicioEvento;

    @NotNull(message = "Data de término do evento é um campo obrigatório")
    @Column(name = "data_termino_evento", nullable = false)
    private LocalDateTime dataTerminoEvento;

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

    @ElementCollection
    @CollectionTable(name = "teste_roteiro", joinColumns = @JoinColumn(name = "teste_id", referencedColumnName = "id"))
    private Set<String> roteiros = new HashSet<>();
    @ElementCollection
    @CollectionTable(name = "teste_resultado", joinColumns = @JoinColumn(name = "teste_id", referencedColumnName = "id"))
    private Set<String> resultados = new HashSet<>();

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

    public LocalDateTime getDataInicioEvento() {
        return dataInicioEvento;
    }

    public void setDataInicioEvento(LocalDateTime dataInicioEvento) {
        this.dataInicioEvento = dataInicioEvento;
    }

    public LocalDateTime getDataTerminoEvento() {
        return dataTerminoEvento;
    }

    public void setDataTerminoEvento(LocalDateTime dataTerminoEvento) {
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

    public Set<String> getRoteiros() {
        return roteiros;
    }

    public void setRoteiros(Set<String> roteiros) {
        this.roteiros = roteiros;
    }

    public Set<String> getResultados() {
        return resultados;
    }

    public void setResultados(Set<String> resultados) {
        this.resultados = resultados;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
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
