package auction.dao;

import auction.persistence.Item;
import javax.annotation.PostConstruct;
import javax.faces.bean.*;

@ManagedBean
@SessionScoped
public class ItemDatasource extends AbstractDatasource<Item> {

	@PostConstruct
	public void init() {
		clazz = Item.class;
	}
	
}
