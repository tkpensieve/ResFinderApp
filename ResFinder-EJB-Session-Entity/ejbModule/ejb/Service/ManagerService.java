package ejb.Service;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ejb.Entity.Manager;

/**
 * Session Bean implementation class ManagerService
 */
@Stateless
@LocalBean
public class ManagerService {
	 @PersistenceContext(unitName="resFinder-ejb-entities")
		EntityManager em;

    /**
     * Default constructor. 
     */
    public ManagerService() {
        
    }
    public String createManager(Manager res) {
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
	public Manager findById(String id)
	{
		Manager u=em.find(Manager.class,id);
		return u;
	}
	public Manager findByUserid(String id)
	{
		return (Manager)em.createQuery("select m from Manager m where m.user.id="+id).getSingleResult();
		
		
	}
	public void delete(String id)
	{
		Manager u=em.find(Manager.class,id);
		em.remove(u);
	}
	public void update(Manager u)
	{
		em.getTransaction().begin();
		em.merge(u);
		em.getTransaction().commit();	
	}

}
