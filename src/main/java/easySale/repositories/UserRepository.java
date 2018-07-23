package easySale.repositories;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import easySale.entities.User;

public class UserRepository {
	
	private EntityManager entityManager;
	
	public void save(User user) {
		entityManager.persist(user);
	}

}
