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
	private String name; 
	private String content;
	
	public Postit(){
		
	}
	
	public Postit (int id, int categoryId, String name, String content){
		this.id = id; 
		this.categoryId = categoryId; 
		this.name = name; 
		this.content = content; 
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	} 
	
}
