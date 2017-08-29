package auction.dao;

import auction.persistence.StandardEntity;
import auction.logic.*;

import javax.naming.InitialContext;

public class Datasource<T extends StandardEntity> extends AbstractDatasource<T> {

	public Datasource(Class<T> clazz) {
		this.clazz = clazz;
		try {
			FactoryEntityService factoryEntityService = (FactoryEntityService)(new InitialContext()).lookup("java:module/FactoryEntityService");
			this.entityService = factoryEntityService.getEntityService();
		} catch (Exception e) {
		}
	}

}
