package auction.web;

import javax.faces.context.FacesContext;
import auction.persistence.User;
import java.util.Map;

public class ApplicationData {

	private static FacesContext context = null;
	
	public static <T> T getBean(String beanName) {
		context = getContext();
		return (T) context.getApplication().evaluateExpressionGet(context, "#{" +  beanName + "}", Object.class);
	}

	public static void setCurrentUser(User user) {
		context = getContext();
		context.getExternalContext().getSessionMap().put("currentUser", user);
	}
	
	public static User getCurrentUser() {
		context = getContext();
		Map<String, Object> map = context.getExternalContext().getSessionMap();
		if(map.containsKey("currentUser"))
			return (User)map.get("currentUser");
		else
			return null;
	}

	public static void closeSession() {
		context = getContext();
		context.getExternalContext().invalidateSession();
	}

	public static FacesContext getContext() {
		return FacesContext.getCurrentInstance();
	}

}
