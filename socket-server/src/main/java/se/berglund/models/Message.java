package se.berglund.models;

import javax.json.JsonArray;

/* A POJO created from the String Json Object from client-side */

public class Message {
	private String type;
	private JsonArray data;
	private int whiteboardId; 
	
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
		return "WhiteboardId " + this.getWhiteboardId() + "Type: " + this.getType() + " Data: " + this.getData().toString(); 
	}

	public int getWhiteboardId() {
		return whiteboardId;
	}

	public void setWhiteboardId(int whiteboardId) {
		this.whiteboardId = whiteboardId;
	}

}
