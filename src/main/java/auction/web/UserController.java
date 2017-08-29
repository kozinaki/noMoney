package auction.web;

import auction.logic.*;
import auction.persistence.*;
import auction.exception.*;
import javax.ejb.EJB;
import javax.faces.bean.*;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;

@ManagedBean
@SessionScoped
public class UserController implements Serializable {

	@EJB
	private FactoryEntityService factoryEntityService;

	private EntityService entityService;

	@PostConstruct
	public void init() {
		entityService = factoryEntityService.getEntityService();
	}
	
	private String login;
	private String password;

	private String loggingMessage;
	private String registeringMessage;

	private String ipAddress;
	
	private boolean visible = true;

	public boolean getVisible() {
		return visible;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	private final String nullString = "";

	public String backFromLogin() {
		login = nullString;
		password = nullString;
		loggingMessage = nullString;
		return "index";
	}
	
	public String backFromRegister() {
		login = nullString;
		password = nullString;
		registeringMessage = nullString;
		return "index";
	}

	public void registering() {
		if (login == null || password == null || login.length() < 4 || password.length() < 4) {
			registeringMessage = "Please enter correct login/password. Minimum length 4 symbols.";
			return;
		}
		//User user = null;
		User user = new User();
		/*try {
			user = entityService.<User>findUser(login);
		} catch(SqlResultException e) {
		}*/
		if (user == null)
			user = new User();
		else {
			//registeringMessage = "This login used for another user.";
			if(entityService  == null)
				registeringMessage = "null";
			else
				registeringMessage = "not null";
			return;
		}
		registeringMessage = "Successful!";
		user.setLogin(login);
		user.setPassword(password);
		entityService.persistEntity(user);
	}

	public String logging() {
		User user = null;
		try {
			user = entityService.<User>findUser(login);
		} catch (SqlResultException e) {
		}
		if (user == null) {
			loggingMessage = "This user was not found.";
			return null;
		}
		if (user.getPassword().equals(password)) {
			ApplicationData.setCurrentUser(user);
			return "welcome";
		} else {
			loggingMessage = "Password incorrect.";
			return null;
		}
	}

	public String getLogin() {
		return login;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getRegisteringMessage() {
		return registeringMessage;
	}
	
	public String getLoggingMessage() {
		return loggingMessage;
	}

	public String getIpAddress() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

		ipAddress = request.getHeader("X-FORWARDED-FOR");
		if(ipAddress == null)
			ipAddress = request.getRemoteAddr();
		return ipAddress;
	}

	public void setRegisteringMessage(String registeringMessage) {
		this.registeringMessage = registeringMessage;
	}
	
	public void setLoggingMessage(String loggingMessage) {
		this.loggingMessage = loggingMessage;
	}
}
