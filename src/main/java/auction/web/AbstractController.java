package auction.web;

import auction.dao.AbstractDatasource;
import auction.persistence.StandardEntity;

import javax.annotation.PostConstruct;

public abstract class AbstractController<T, E extends StandardEntity> implements IController<T> {

	protected AbstractDatasource<E> mainDatasource = null;

	protected E entity;
	
	public AbstractDatasource<E> getMainDatasource() {
		return mainDatasource;
	}

	public void setMainDatasource(AbstractDatasource<E> mainDatasource) {
		this.mainDatasource = mainDatasource;
	}

	public E getEntity() {
		return entity;
	}

	public void setEntity(E entity) {
		this.entity = entity;
	}

	public void newEntity() {
		entity = mainDatasource.newEntity();
	}

	@PostConstruct
	public abstract void init();
}
