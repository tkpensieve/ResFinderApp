package ejb.Service;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

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

    public String createAddRestaurantRequest(AddRestaurantRequest res) {
		try{
			em.persist(res);
			return "Success";
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
	public ArrayList<AddRestaurantRequest> findByName(String name)
	{
		//return (ArrayList<AddRestaurantRequest>) em.createQuery("SELECT a FROM AddRestaurantRequest a where a.restaurantName="+name ).getResultList();
		TypedQuery<AddRestaurantRequest> query = em.createNamedQuery("AddRestaurantRequest.fetchrequest", AddRestaurantRequest.class);
        query.setParameter("name", name);
        
		return  (ArrayList<AddRestaurantRequest>)query.getResultList();
	}
	@SuppressWarnings("unchecked")
	public ArrayList<AddRestaurantRequest> getRequests()
    {
    	ArrayList<AddRestaurantRequest> results = (ArrayList<AddRestaurantRequest>) em.createQuery("SELECT a FROM AddRestaurantRequest a where a.status='PENDING'").getResultList();
		return results;
    }
}
