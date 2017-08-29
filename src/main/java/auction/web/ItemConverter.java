package auction.web;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.*;
import javax.faces.bean.*;
import java.util.Map;

import auction.persistence.Item;
import auction.dao.*;

@FacesConverter("itemConverter")
public class ItemConverter implements Converter {

	private Datasource itemDatasource;

	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		if(itemDatasource == null)
			itemDatasource = (ApplicationData.<LotController>getBean("lotController")).getItemDatasource();
		return (Item)(itemDatasource.getMap().get(value));
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
		if(object != null) {
			return ((Item)object).getId();
		}
		return null;
	}

}
