package se.berglund.models;

import javax.json.JsonArray;

public class Message {
	private String type;
	private JsonArray data;
	
	public Message(){
	}; 
	
	public Message(String type, JsonArray data){
		this.type = type; 
		this.data = data; 
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public JsonArray getData() {
		return data;
	}
	public void setData(JsonArray data) {
		this.data = data;
	} 
	
	@Override
	public String toString(){
		return "Type: " + this.getType() + " Data: " + this.getData().toString(); 
	}

}
