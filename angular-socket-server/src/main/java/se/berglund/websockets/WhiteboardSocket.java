package se.berglund.websockets;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/whiteboards", encoders = { JsonEncoder.class }, decoders = { JsonDecoder.class })
public class WhiteboardSocket {

	@OnOpen
	public void myOnOpen(Session session) {
		System.out.println("Angular WhiteboardSocket connected");
	}

	@OnMessage
	public String myOnMessage(Session session, String message) {

		System.out.println("Message recived " + message);
		
		for (Session s : session.getOpenSessions()) {
			if (s.isOpen()) {
				s.getAsyncRemote().sendText(message);
			}
		}
		return message;
	}

	@OnClose
	public void myOnClose(Session session, CloseReason reason) {
		System.out.println("Angular WhiteboardSocket closed");
	}

	@OnError
	public void myOnError(Session session, Throwable throwable) {
		System.out.println("Angular WhiteboardSocket Error");
	}
}
