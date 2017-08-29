package auction.persistence;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="lot_s")
public class Lot extends StandardEntity implements Serializable {
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "give_item_id")
	private Item giveItem;

	@Column(name = "give_number")
	private String giveNumber;

	@Column(name = "take_number")
	private String takeNumber;

	@Column(name = "take_name")
	private String takeName;
	
	@Column(name = "take_description")
	private String takeDescription;
	
	@Column(name = "take_image")
	private Byte[] takeImage;
	
	public Item getGiveItem() {
		return giveItem;
	}
	
	public void setGiveItem(Item item) {
		this.giveItem = item;
	}
	
	public String getGiveNumber() {
		return giveNumber;
	}
	
	public void setGiveNumber(String number) {
		this.giveNumber = number;
	}
	
	public String getTakeNumber() {
		return takeNumber;
	}
	
	public void setTakeNumber(String number) {
		this.takeNumber = number;
	}

	public String getTakeName() {
		return takeName;
	}
	
	public void setTakeName(String name) {
		this.takeName = name;
	}

	public String getTakeDescription() {
		return takeDescription;
	}
	
	public void setTakeDescription(String description) {
		this.takeDescription = description;
	}

	public Byte[] getTakeImage() {
		return takeImage;
	}
	
	public void setTakeImage(Byte[] image) {
		this.takeImage = image;
	}

	@Override
	public String toString() {
		return takeName;
	}

}
