/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.edsondev.repository;

import br.com.edsondev.entities.Aluno;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author junior
 */
@Stateless
public class AlunoFacade extends AbstractFacade<Aluno> {

    @PersistenceContext(unitName = "jndiPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
	return em;
    }

    public AlunoFacade() {
	super(Aluno.class);
    }

    public Aluno findByEmail(String email) {
	try {
	    TypedQuery<Aluno> query = em.createQuery("SELECT a FROM Aluno a WHERE a.email = :pEmail", Aluno.class);
	    query.setParameter("pEmail", email);

	    return query.getSingleResult();
	} catch (NoResultException e) {
	    return null;
	}
    }

}
