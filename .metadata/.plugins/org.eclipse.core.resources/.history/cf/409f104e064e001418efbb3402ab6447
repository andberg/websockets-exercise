package se.berglund.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="postits")
public final class Postit {
	@Id
	@GeneratedValue 
	private int id; 
	@Column(name="category_id")
	private int categoryId; 
	@Column(name="whiteboard_id")
	private int whiteboardId; 
	private String title; 
	private String description;
	private String color; 
	
	public Postit(){}
	
	public Postit (int id, int categoryId, int whiteboardId, String title, String description){
		this.id = id; 
		this.categoryId = categoryId; 
		this.setTitle(title); 
		this.setDescription(description); 
		this.whiteboardId = whiteboardId; 
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	} 
	public int getWhiteboardId() {
		return whiteboardId;
	}
	public void setWhiteboardId(int whiteboardId) {
		this.whiteboardId = whiteboardId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	} 
	
}
