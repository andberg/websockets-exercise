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
		System.out.println("Encoder destroyd");
	}

	@Override
	public void init(EndpointConfig arg0) {
		System.out.println("Encoder init");
	}

	@Override
	public String encode(Message message) throws EncodeException {
		JsonObject jsonObject = Json.createObjectBuilder()
				.add("type", message.getType())
				.add("dataArray", message.getData()).build();
		return jsonObject.toString();
	}
}
