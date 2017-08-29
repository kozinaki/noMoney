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
public class ListLotController extends AbstractController<ListLotController, Lot> {

	private Lot selectedLot;

	public List<Lot> getData() {
		if(mainDatasource.getList().size() != 0 && selectedLot == null)
			selectedLot = mainDatasource.getList().get(0);
		return mainDatasource.getList();
	}

	public void delete(Lot lot) {
		mainDatasource.delete(lot);
		if(lot.getId() != null && lot.getId().equals(selectedLot.getId()) && mainDatasource.getList().size() > 0)
			selectedLot = mainDatasource.getList().get(0);
		else if(mainDatasource.getList().size() == 0)
			selectedLot = null;
	}

	public void setSelectedLot(Lot selectedLot) {
		this.selectedLot = selectedLot;
	}
	
	public Lot getSelectedLot() {
		return selectedLot;
	}

	@Override
	public void init() {
		mainDatasource = new Datasource<Lot>(Lot.class);
	}
	
	@Override
	public void update() {
		getMainDatasource().refresh();
	}

	@Override
	public ListLotController get() {
		return this;
	}
}
