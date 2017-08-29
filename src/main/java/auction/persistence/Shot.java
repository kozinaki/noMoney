package auction.persistence;

import java.io.Serializable;
import javax.persistence.*;
import javax.faces.bean.*;

@Entity
@Table(name="shot_s")
@ManagedBean
@RequestScoped
public class Shot extends StandardEntity implements Serializable {

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "lot_id")
	private Lot lot;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "take_item_id")
	private Item takeItem;

	@Column(name = "take_number")
	private String takeNumber;

	public void setLot(Lot lot) {
		this.lot = lot;
	}

	public Lot getLot() {
		return lot;
	}

	public void setTakeItem(Item takeItem) {
		this.takeItem = takeItem;
	}
	
	public Item getTakeItem() {
		return takeItem;
	}

	public void setTakeNumber(String takeNumber) {
		this.takeNumber = takeNumber;
	}

	public String getTakeNumber() {
		return takeNumber;
	}

	@Override
	public String toString() {
		return lot.getGiveItem().getName() + " " +  takeItem.getName();
	}
}
