package ejb.Service;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.*;

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
	
	public ArrayList<Location> findAll()
	{
		ArrayList<Location> allLocations = new ArrayList<Location>();
		List<Location> results = em.createQuery("SELECT p FROM Person p").getResultList();
		if(results.size() > 0)
			allLocations.addAll(results);
		return allLocations;
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

}
