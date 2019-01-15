/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.registry.infomodel.User;
import servlets.Users;

/**
 *
 * @author pupil
 */
@Stateless
public class UsersFacade extends AbstractFacade<Users> {

    @PersistenceContext(unitName = "MySecurityBlogPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsersFacade() {
        super(Users.class);
    }
    private User findBuyLogin(String username) {
        try {
            return (User) em.createQuery("SELECT u FROM User u.login = :username")
                    .setParameter("username", username)
                    .getResultList();
        } catch (Exception e) {
            return null;
        }
        
    }
}
