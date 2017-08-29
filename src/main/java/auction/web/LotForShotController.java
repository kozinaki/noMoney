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
public class LotForShotController extends AbstractController<LotForShotController, Shot> {

	private Datasource<Lot> lotDatasource;

	private Datasource<Item> itemDatasource;

	private Lot selectedLot;

	private String result;

	public Datasource getItemDatasource() {
		return itemDatasource;
	}

	public Datasource getLotDatasource() {
		return lotDatasource;
	}

	public Map<String, Item> getItemData() {
		return itemDatasource.getMap();
	}

	public List<Lot> getLotData() {
		if(lotDatasource.getList().size() != 0 && selectedLot == null)
			selectedLot = lotDatasource.getList().get(0);
		return lotDatasource.getList();
	}
	
	public void setTakeItem(ValueChangeEvent event) {
		entity.setTakeItem((Item)event.getNewValue());
	}

	public Item getTakeItem() {
		return entity.getTakeItem();
	}

	public void setTakeNumber(ValueChangeEvent event) {
		entity.setTakeNumber(event.getNewValue().toString());
	}

	public void saveItem(AjaxBehaviorEvent event) {
		mainDatasource.add(entity);
		result = "Shot saved!";
		entity.setId(null);
		//entity = mainDatasource.newEntity();
	}
	
	public void setSelectedLot(Lot selectedLot) {
		this.selectedLot = selectedLot;
		entity.setLot(selectedLot);
	}
	
	public Lot getSelectedLot() {
		return selectedLot;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getResult() {
		return result;
	}

	@Override
	public void init() {
		mainDatasource = new Datasource<Shot>(Shot.class);
		lotDatasource = new Datasource<Lot>(Lot.class);
		itemDatasource = new Datasource<Item>(Item.class);
		entity = mainDatasource.newEntity();
	}
	
	@Override
	public void update() {
		lotDatasource.refresh();
		itemDatasource.toMap();
		if(itemDatasource.getList().size() > 0)
			entity.setTakeItem(itemDatasource.getList().get(0));
		setResult("");
	}

	@Override
	public LotForShotController get() {
		return this;
	}

}
