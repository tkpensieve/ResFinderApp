package ejb.Service;
import java.util.Date;

import javax.persistence.*;

import ejb.Entity.*;

import javax.ejb.Stateless;

@Stateless
public class HelloService {
    @PersistenceContext(unitName="resFinder-ejb-entities")
	EntityManager em;

	public String createHelloMessage(String name) {
		Cuisine newEntity = new Cuisine();
		
		long currentTime = new Date().getTime();
//		newEntity.setId(123); //No need to set if auto-generated
		String message = "Hello " + name + currentTime + "!";
		newEntity.setDescription(message);
		newEntity.setName(message);
		em.persist(newEntity);
		long beanID = newEntity.getId();
		return "Servlet Session Bean Entity " + "ID =" + beanID + " " + message;
	}
}
