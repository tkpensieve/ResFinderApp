package ejb.Service;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.*;


import ejb.Entity.AddRestaurantRequest;

/**
 * Session Bean implementation class AddRestaurantRequestService
 */
@Stateless
@LocalBean
public class RequestService {
	 @PersistenceContext(unitName="resFinder-ejb-entities")
		EntityManager em;

    /**
     * Default constructor. 
     */
   
    public String createAddRestaurantRequest(AddRestaurantRequest res) {
		try{
		em.persist(res);
		int beanID = res.getId();
		return "Servlet Session Bean Entity " + "ID =" + beanID;
		}
		catch(Exception e)
		{
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			return errors.toString();
		}
		
		
			
	}
	public AddRestaurantRequest findById(int id)
	{
		AddRestaurantRequest u=em.find(AddRestaurantRequest.class,id);
		return u;
	}
	public void delete(int id)
	{
		AddRestaurantRequest u=em.find(AddRestaurantRequest.class,id);
		em.remove(u);
	}
	public void update(AddRestaurantRequest u)
	{
		
		em.getTransaction().begin();
		em.merge(u);
		em.getTransaction().commit();
		
		
	}

}
