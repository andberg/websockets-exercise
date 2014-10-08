package se.berglund.models;

import javax.websocket.Session;

public class Client {
	
	private Session session; 
	private int whiteboardId;
	
	public Client(Session session){
		this.setSession(session); 
	}
	public Client(){}
	
	
	public int getWhiteboardId() {
		return whiteboardId;
	}
	public void setWhiteboardId(int whiteboardId) {
		this.whiteboardId = whiteboardId;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	} 
	
	public int changeWhiteboard(int id){
		if(id != whiteboardId)
		{
			whiteboardId = id; 
		}
		return whiteboardId; 
	}
	
}
