package auction.dao;

import auction.persistence.Lot;
import javax.annotation.PostConstruct;
import javax.faces.bean.*;

@ManagedBean
@SessionScoped
public class LotDatasource extends AbstractDatasource<Lot> {

	@PostConstruct
	public void init() {
		clazz = Lot.class;
	}
	
}
