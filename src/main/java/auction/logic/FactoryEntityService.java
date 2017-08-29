package auction.logic;

import javax.ejb.*;

@Singleton
@Startup
public class FactoryEntityService {

	private EntityService entityService;

	@EJB
	@Lock(LockType.READ)
	public void setEntityService(EntityService entityService) {
		this.entityService = entityService;
	}

	public EntityService getEntityService() {
		return entityService;
	}

}
