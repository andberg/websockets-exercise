package se.berglund.websockets;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import se.berglund.models.Message;

@ServerEndpoint(value = "/whiteboards", encoders = { JsonEncoder.class }, decoders = { JsonDecoder.class })
public class WhiteboardSocket {

	@OnOpen
	public void myOnOpen(Session session) {
		System.out.println("Angular WhiteboardSocket connected, session id: "
				+ session.getId().toString());
	}

	@OnMessage
	public void myOnMessage(Session session, Message message) {

		System.out.println("Message recived " + message.toString());
		
		/*Message response = new Message();
		response.setSubject("Response to " + message.getSubject());
		response.setContent("echo " + message.getContent());*/

		for (Session s : session.getOpenSessions()) {
			if (s.isOpen()) {
				s.getAsyncRemote().sendObject(message);
			}
		}
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