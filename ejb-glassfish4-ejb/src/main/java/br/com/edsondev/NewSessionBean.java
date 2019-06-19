package br.com.edsondev;

import javax.ejb.Stateless;
import javax.ejb.LocalBean;

@Stateless
@LocalBean
public class NewSessionBean {

    public void businessMethod() {
        System.out.println("Estou sendo executado...");
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
