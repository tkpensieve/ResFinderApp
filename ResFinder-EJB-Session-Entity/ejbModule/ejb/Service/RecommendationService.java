package ejb.Service;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.*;

import ejb.Entity.Recommendation;
import ejb.Entity.Restaurant;

/**
 * Session Bean implementation class ReviewService
 */
@Stateless
@LocalBean
public class RecommendationService {
	@PersistenceContext(unitName="resFinder-ejb-entities")
	EntityManager em;

    public ArrayList<Recommendation>findByUser(String id)
    {
    	TypedQuery<Recommendation> query= em.createNamedQuery("Recommendation.fetchRec",Recommendation.class);
    	query.setParameter("id", id);
    	return (ArrayList<Recommendation>) query.getResultList();	
    }
    
    public Restaurant fetchRes(String id)
    {
    	TypedQuery<Restaurant> query= em.createNamedQuery("Recommendation.fetchRes",Restaurant.class);
    	query.setParameter("id", id);
    	return (Restaurant) query.getSingleResult();
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
