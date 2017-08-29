package auction.persistence;

import java.io.Serializable;
import javax.persistence.*;
import javax.faces.bean.*;

@Entity
@Table(name="item_s")
@ManagedBean
@RequestScoped
public class Item extends StandardEntity implements Serializable {

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "user_id")
	private User user;
		
	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;	

	@Column(name = "image")
	private Byte[] image;

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setImage(Byte[] image) {
		this.image = image;
	}

	public Byte[] getImage() {
		return image;
	}

	@Override
	public String toString() {
		return name;
	}
}
