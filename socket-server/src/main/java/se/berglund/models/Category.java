package se.berglund.models;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
public final class Category {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private int whiteboardId;
	private ArrayList<Postit> postits = new ArrayList<Postit>(); 

	public Category() {

	}

	public Category(int id, String name, int whiteboardId) {
		this.id = id;
		this.name = name;
		this.whiteboardId = whiteboardId;
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

	public int getWhiteboardId() {
		return whiteboardId;
	}

	public void setWhiteboardId(int whiteboardId) {
		this.whiteboardId = whiteboardId;
	}

	public ArrayList<Postit> getPostits() {
		return postits;
	}

	public void setPostits(ArrayList<Postit> postits) {
		this.postits = postits;
	}

}
