package auction.web;

import org.primefaces.component.panelgrid.PanelGrid;
import org.primefaces.component.column.Column;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.api.UIColumn;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.event.AjaxBehaviorEvent;
import java.util.*;
import javax.faces.bean.*;
import java.io.Serializable;
import auction.persistence.*;
import auction.logic.*;
import javax.ejb.EJB;
import javax.annotation.PostConstruct;

@ManagedBean
@SessionScoped
public class WelcomeController implements Serializable {

	private IController controller;

	private Map<String, String> mapTemplates = new HashMap();

	private String dynamicTemplate;

	private boolean visible = false;

	@PostConstruct
	public void init() {
		mapTemplates.put("lotForShot", "/pages/templates/lotForShot.xhtml");
		mapTemplates.put("addItem", "/pages/templates/addItem.xhtml");
		mapTemplates.put("addLot", "/pages/templates/addLot.xhtml");
		mapTemplates.put("myItems", "/pages/templates/myItems.xhtml");
		mapTemplates.put("myLots", "/pages/templates/myLots.xhtml");
		mapTemplates.put("defaultTemplate", "/pages/templates/default.xhtml");
		dynamicTemplate = mapTemplates.get("defaultTemplate");
	}

	public String logout() {
		ApplicationData.closeSession();
		return "index";
	}

	public boolean getVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public String getDynamicTemplate() {
		return dynamicTemplate;
	}

	public void setDynamicTemplate(String dynamicTemplate) {
		this.dynamicTemplate = dynamicTemplate;
	}

	public void template(String nameTemplate, String nameController) {
		setController(getManagedBean(nameController));
		controller.update();
		
		String template = mapTemplates.get(nameTemplate);		

		if(!dynamicTemplate.equals(template))
			dynamicTemplate = template;
		else
			dynamicTemplate = mapTemplates.get("defaultTemplate");
	}

	public IController getController() {
		return controller;
	}

	public void setController(IController controller) {
		this.controller = controller;
	}

	public IController getManagedBean(String beanName) {
		return ApplicationData.<IController>getBean(beanName);
	}

}
