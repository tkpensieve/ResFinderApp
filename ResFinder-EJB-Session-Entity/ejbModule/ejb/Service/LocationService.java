package ejb.Service;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.*;

import ejb.Entity.Cuisine;
import ejb.Entity.Location;

/**
 * Session Bean implementation class LocationService
 */
@Stateless
@LocalBean
public class LocationService {
	@PersistenceContext(unitName="resFinder-ejb-entities")
	EntityManager em;

	public String createLocation(Location res) {
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
	public ArrayList<Location> findAll()
	{
		ArrayList<Location> results = (ArrayList<Location>) em.createQuery("SELECT l FROM Location l").getResultList();
		return results;
	}
	
	public Location findById(int id)
	{
		Location u=em.find(Location.class,id);
		return u;
	}
	
	public void delete(int id)
	{
		Location u=em.find(Location.class,id);
		em.remove(u);
	}
	
	public void update(Location u)
	{
		em.getTransaction().begin();
		em.merge(u);
		em.getTransaction().commit();	
	}
	public Location findLoc(String name)
	{
		return (Location)em.createQuery("select c from location c where c.name="+name);
	}

}
