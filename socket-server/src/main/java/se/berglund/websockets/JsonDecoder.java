package se.berglund.websockets;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import se.berglund.models.Message;

public class JsonDecoder implements Decoder.Text<Message> {

	@Override
	public Message decode(String jsonMessage) throws DecodeException {
		
		JsonObject jsonObject = Json
				.createReader(new StringReader(jsonMessage)).readObject();
		Message message = new Message();
		message.setWhiteboardId(jsonObject.getInt("whiteboard"));
		message.setType(jsonObject.getString("type"));
		message.setData(jsonObject.getJsonArray("dataArray"));
		return message;
	}

	@Override
	public boolean willDecode(String jsonMessage) {
		try {
			Json.createReader(new StringReader(jsonMessage)).readObject();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public void init(EndpointConfig ec) {
		System.out.println("MessageDecoder -init method called");
	}

	@Override
	public void destroy() {
		System.out.println("MessageDecoder - destroy method called");
	}

}