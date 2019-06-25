/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.edsondev.repository;

import br.com.edsondev.entities.InformacaoExtra;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author junior
 */
@Stateless
public class InformacaoExtraFacade extends AbstractFacade<InformacaoExtra> {

    @PersistenceContext(unitName = "jndiPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InformacaoExtraFacade() {
        super(InformacaoExtra.class);
    }

}
