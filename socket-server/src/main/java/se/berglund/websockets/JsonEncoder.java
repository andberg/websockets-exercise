package se.berglund.websockets;

import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.EncodeException;
import javax.websocket.Encoder.Text;
import javax.websocket.EndpointConfig;

import se.berglund.models.Message;

public class JsonEncoder implements Text<Message> {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(EndpointConfig arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public String encode(Message message) throws EncodeException {
		JsonObject jsonObject = Json.createObjectBuilder()
		        .add("subject", message.getSubject())
		        .add("content", message.getContent()).build();
		    return jsonObject.toString();
	}
}
