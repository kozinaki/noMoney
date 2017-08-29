package auction.web;

import auction.logic.*;
import auction.persistence.*;
import auction.exception.*;
import auction.dao.*;
import javax.ejb.EJB;
import javax.faces.bean.*;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@SessionScoped
public class ItemController extends AbstractController<ItemController, Item> {

	private Item selectedItem;

	private String result;

	public void saveItem(AjaxBehaviorEvent event) {
		entity.setUser(ApplicationData.getCurrentUser());
		mainDatasource.add(entity);
		result = "Item saved!";
		entity.setId(null);
	}

	public List<Item> getData() {
		if(mainDatasource.getList().size() != 0 && selectedItem == null)
			selectedItem = mainDatasource.getList().get(0);
		return mainDatasource.getList();
	}

	public void delete(Item item) {
		mainDatasource.delete(item);
		if(item.getId() != null && item.getId().equals(selectedItem.getId()) && mainDatasource.getList().size() > 0)
			selectedItem = mainDatasource.getList().get(0);
		else if(mainDatasource.getList().size() == 0)
			selectedItem = null; 
	}

	public void setSelectedItem(Item selectedItem) {
		this.selectedItem = selectedItem;
	}
	
	public Item getSelectedItem() {
		return selectedItem;
	}

	public void setName(ValueChangeEvent event) {
		entity.setName(event.getNewValue().toString());
	}
	
	public void setDescription(ValueChangeEvent event) {
		entity.setDescription(event.getNewValue().toString());
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public void init() {
		mainDatasource = new Datasource<Item>(Item.class);
		entity = mainDatasource.newEntity();
	}
	
	@Override
	public void update() {
		getMainDatasource().refresh();
		setResult("");
	}

	@Override
	public ItemController get() {
		return this;
	}
}
