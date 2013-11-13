package ejb.Service;
import javax.persistence.*;

import ejb.Entity.*;

import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import java.io.*;
@Stateless
@LocalBean
public class UserService {
    @PersistenceContext(unitName="resFinder-ejb-entities")
	EntityManager em;

	public String createUser(User user) {
		try{
			em.persist(user);
			String beanID = user.getId();
			return "Servlet Session Bean Entity " + "ID =" + beanID;
		}
		catch(Exception e)
		{
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			return errors.toString();
		}	
	}
	public User findById(String id)
	{
		User u=em.find(User.class,id);
		return u;
	}
	public void delete(String id)
	{
		User u=em.find(User.class,id);
		em.remove(u);
	}
	public void update(User u)
	{
		em.getTransaction().begin();
		em.merge(u);
		em.getTransaction().commit();
	}
}
