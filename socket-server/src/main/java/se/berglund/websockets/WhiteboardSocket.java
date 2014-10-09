package se.berglund.websockets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import se.berglund.messagehandler.MessageHandler;
import se.berglund.models.Client;
import se.berglund.models.Message;

/* Websocket connection to Angular Floggit Whiteboard application
 * Localhost/9000/# */

@ServerEndpoint(value = "/whiteboards", encoders = { JsonEncoder.class }, decoders = { JsonDecoder.class })
public class WhiteboardSocket {

	List<Client> clients = Collections
			.synchronizedList(new ArrayList<Client>());

	@OnOpen
	public void myOnOpen(Session session) {
		Client client = new Client(session);
		clients.add(client);
	}

	@OnMessage
	public void myOnMessage(Session session, Message message) {
		System.out.println(message); 
		
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
