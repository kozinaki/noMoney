package auction.persistence;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.*;

@Entity
@Table(name="user_s")
@ManagedBean
@SessionScoped
public class User extends StandardEntity implements Serializable {
	
	@Column(name="name")
	private String name;

	@Column(name="login")
	private String login;
	
	@Column(name="password")
	private String password;

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinTable(name="user_item_s", 
		joinColumns=@JoinColumn(name="user_id"),
		inverseJoinColumns=@JoinColumn(name="item_id"))
	private List<Item> items;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setItems(List<Item> items) {
		this.items = items;
	}
	
	public List<Item> getItems() {
		return items;
	}

	@Override
	public String toString() {
		return login;
	}
}
