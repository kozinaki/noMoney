package auction.logic;

import auction.persistence.StandardEntity;
import auction.persistence.User;
import auction.persistence.Item;
import auction.exception.*;
import javax.ejb.Stateless;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.persistence.NoResultException;
import java.util.*;

@Stateless//(name="ItemService", mappedName="openjpa/ItemService")
public class EntityServiceBean implements EntityService {

	@PersistenceContext(unitName = "eclipseLink")
	private EntityManager entityManager;

	public <T> T getEntity(Class<T> entityClass, long entityId) {
		return entityManager.<T>find(entityClass, entityId);
	}
		
	public void flush() {
		entityManager.flush();
	}

	public String persistEntity(StandardEntity entity) {
		entityManager.persist(entity);
		return "Well done!";
	}
	
	public String persistEntity(StandardEntity entityOne, StandardEntity entityTwo) {
		entityManager.persist(entityOne);
		entityManager.persist(entityTwo);
		return "Well done!";
	}
	
	public <T extends StandardEntity> T mergeEntity(T entity) {
		return entityManager.merge(entity);
	}
	
	public <T extends User> T findUser(String login) throws SqlResultException {
		Query query = entityManager.createQuery("select u from User u where u.login = :userLogin");
		query.setParameter("userLogin", login);
		T user;
		try {
			user = (T)query.getSingleResult();
		} catch(NoResultException e) {
			throw new SqlResultException("No result in this query.");
		}
		return user;
	}

	public List<Item> findItems() {
		Query query = entityManager.createQuery("select i from Item i");
		return query.getResultList();
	}

	public <T extends StandardEntity> List<T> getEntities(Class<T> clazz) {
		String strQuery = "SELECT entity FROM " + clazz.getName() + " entity";
		Query query = entityManager.createQuery(strQuery);
		return query.getResultList();
	}

	public <T extends StandardEntity> void removeEntity(T standardEntity) {
		standardEntity = entityManager.merge(standardEntity);
		entityManager.remove(standardEntity);
	}
}
