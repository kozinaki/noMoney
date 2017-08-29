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
import java.util.Map;

@ManagedBean
@SessionScoped
public class LotController extends AbstractController<LotController, Lot> {

	private Datasource<Item> itemDatasource;
	
	private String result;

	public Datasource getItemDatasource() {
		return itemDatasource;
	}

	public Map<String, Item> getData() {
		return itemDatasource.getMap();
	}

	public void saveLot(AjaxBehaviorEvent event) {
		mainDatasource.add(entity);
		result = "Lot saved!";
		entity.setId(null);
		//entity = mainDatasource.newEntity();
	}
	
	public void setGiveItem(ValueChangeEvent event) {
		entity.setGiveItem((Item)event.getNewValue());
	}

	public Item getGiveItem() {
		return entity.getGiveItem();
	}
	
	public void setGiveNumber(ValueChangeEvent event) {
		entity.setGiveNumber(event.getNewValue().toString());
	}
	
	public void setTakeNumber(ValueChangeEvent event) {
		entity.setTakeNumber(event.getNewValue().toString());
	}

	public void setTakeName(ValueChangeEvent event) {
		entity.setTakeName(event.getNewValue().toString());
	}

	public void setTakeDescription(ValueChangeEvent event) {
		entity.setTakeDescription(event.getNewValue().toString());
	}

	public void setTakeImage(ValueChangeEvent event) {
		
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getResult() {
		return result;
	}

	@Override
	public void init() {
		mainDatasource = new Datasource<Lot>(Lot.class);
		itemDatasource = new Datasource(Item.class);
		entity = mainDatasource.newEntity();
	}

	@Override
	public void update() {
		itemDatasource.toMap();
		if(itemDatasource.getList().size() > 0)
			entity.setGiveItem(itemDatasource.getList().get(0));
		setResult("");
	}

	@Override
	public LotController get() {
		return this;
	}
}
