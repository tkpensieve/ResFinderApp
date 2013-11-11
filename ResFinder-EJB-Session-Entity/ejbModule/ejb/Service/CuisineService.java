package ejb.Service;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.*;

import ejb.Entity.Cuisine;
import ejb.Entity.Restaurant;

/**
 * Session Bean implementation class CuisineService
 */
@Stateless
@LocalBean
public class CuisineService {
	 @PersistenceContext(unitName="resFinder-ejb-entities")
		EntityManager em;

   
	
    public String createCuisine(Cuisine res) {
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
    
    @SuppressWarnings("unchecked")
	public ArrayList<Cuisine> findAll()
	{
		ArrayList<Cuisine> results = (ArrayList<Cuisine>) em.createQuery("SELECT c FROM Cuisine c").getResultList();
		return results;
	}
    
	public Cuisine findById(int id)
	{
		Cuisine u=em.find(Cuisine.class,id);
		return u;
	}
	public void delete(int id)
	{
		Cuisine u=em.find(Cuisine.class,id);
		em.remove(u);
	}
	public void update(Cuisine u)
	{
		
		em.getTransaction().begin();
		em.merge(u);
		em.getTransaction().commit();
	
	}
	public ArrayList<Cuisine> findCuisine(String name)
	{
		
		
		TypedQuery<Cuisine> query = em.createNamedQuery("Cuisine.fetchCuisine", Cuisine.class);
        query.setParameter("cuis", name);
        
		return (ArrayList<Cuisine>) query.getResultList();
		
		
	}


}
