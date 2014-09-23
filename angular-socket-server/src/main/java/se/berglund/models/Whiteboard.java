package se.berglund.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

;

@Entity
@Table(name = "whiteboards")
public final class Whiteboard {
	@Id
	@GeneratedValue
	private int id;
	private String name;

	public Whiteboard() {

	}

	public Whiteboard(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
