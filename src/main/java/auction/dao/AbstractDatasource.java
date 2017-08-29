package auction.dao;

import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import auction.logic.*;
import auction.persistence.StandardEntity;

public abstract class AbstractDatasource <T extends StandardEntity> {

	protected EntityService entityService /*= FactoryEntityService.getEntityService()*/;

	protected List<T> list = null;

	protected Map<String, T> map = new LinkedHashMap();

	protected Class<T> clazz = null;
	
	public void setTypeEntity(Class<T> clazz) {
		this.clazz = clazz;
	}

	public T newEntity() {
		try {
			return clazz.newInstance();
		} catch(InstantiationException | IllegalAccessException e) {
			return null;
		}
	}

	public void refresh() {
		list = entityService.getEntities(clazz);
	}

	public void delete(T entity) {
		entityService.removeEntity(entity);
		refresh();
	}

	public void add(T entity) {
		entityService.persistEntity(entity);
		refresh();
	}

	public List<T> getList() {
		return list;
	}

	public Map<String, T> getMap() {
		return map;
	}

	public void toMap() {
		refresh();
		map.clear();
		for(T entity : list) map.put(entity.getId(), entity);
	}
}
