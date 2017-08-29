package auction.persistence;

import javax.persistence.*;
import org.eclipse.persistence.annotations.UuidGenerator;

@MappedSuperclass
public class StandardEntity {
	
	@Id
	@UuidGenerator(name="uuid")
	@GeneratedValue(generator="uuid")//(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	protected String id;
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}

	public String getType() {
		return this.getClass().getName();
	}
}
