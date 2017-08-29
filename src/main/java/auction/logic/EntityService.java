package auction.logic;

import auction.persistence.StandardEntity;
import auction.persistence.User;
import javax.ejb.Local;
import javax.persistence.NoResultException;
import auction.exception.*;
import java.util.List;
import auction.persistence.Item;

@Local
public interface EntityService {

	<T> T getEntity(Class<T> entityClass, long entityId);
	
	String persistEntity(StandardEntity entity);

	String persistEntity(StandardEntity entityOne, StandardEntity entityTwo);
	
	<T extends StandardEntity> T mergeEntity(T entity);
	
	<T extends User> T findUser(String login) throws SqlResultException;

	List<Item> findItems();

	<T extends StandardEntity> List<T> getEntities(Class<T> clazz);

	<T extends StandardEntity> void removeEntity(T standardEntity);
	
	void flush();
}
