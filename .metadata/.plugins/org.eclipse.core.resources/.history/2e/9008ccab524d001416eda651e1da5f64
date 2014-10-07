package se.berglund.websockets;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import se.berglund.messagehandlers.MessageHandler;
import se.berglund.models.Message;

@ServerEndpoint(value = "/whiteboards", encoders = { JsonEncoder.class }, decoders = { JsonDecoder.class })
public class WhiteboardSocket {

	@OnOpen
	public void myOnOpen(Session session) {
		System.out.println("WhiteboardSocket connected, session id: "
				+ session.getId().toString());
	}

	@OnMessage
	public void myOnMessage(Session session, Message message) {
		System.out.println(message.getData()); 
		MessageHandler messageHandler = new MessageHandler(message); 
		Message handeldMessage = messageHandler.handleMessage(); 
		
		for (Session s : session.getOpenSessions()) {
			if (s.isOpen()) {
				s.getAsyncRemote().sendObject(handeldMessage);
			}
		}
	}

	@OnClose
	public void myOnClose(Session session, CloseReason reason) {
		System.out.println("WhiteboardSocket closed");
	}

	@OnError
	public void myOnError(Session session, Throwable throwable) {
		throwable.printStackTrace();
	}
}
