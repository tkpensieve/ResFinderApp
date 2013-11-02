package ejb.Service;
import javax.persistence.*;

import ejb.Entity.*;

import javax.ejb.Stateless;

@Stateless
public class UserService {
    @PersistenceContext(unitName="resFinder-ejb-entities")
	EntityManager em;

	public String createUser(User user) {
		em.persist(user);
		String beanID = user.getId();
		return "Servlet Session Bean Entity " + "ID =" + beanID;
	}
}
