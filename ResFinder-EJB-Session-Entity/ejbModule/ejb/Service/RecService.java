package ejb.Service;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.*;


import ejb.Entity.Recommendation;

/**
 * Session Bean implementation class RecommendationService
 */
@Stateless
@LocalBean
public class RecService {
	 @PersistenceContext(unitName="resFinder-ejb-entities")
		EntityManager em;

    /**
     * Default constructor. 
     */
   
    public String createRecommendation(Recommendation res) {
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
	public Recommendation findById(int id)
	{
		Recommendation u=em.find(Recommendation.class,id);
		return u;
	}
	public void delete(int id)
	{
		Recommendation u=em.find(Recommendation.class,id);
		em.remove(u);
	}
	public void update(Recommendation u)
	{
		
		em.getTransaction().begin();
		em.merge(u);
		em.getTransaction().commit();
		
		
	}

}
