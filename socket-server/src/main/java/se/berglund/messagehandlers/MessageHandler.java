package se.berglund.messagehandlers;

import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

import se.berglund.datastorage.CurrentWhiteboardManager;
import se.berglund.datastorage.WhiteboardManager;
import se.berglund.models.Message;
import se.berglund.models.Whiteboard;

public class MessageHandler {
	private Message message;

	public MessageHandler(Message message) {
		this.message = message;
	}

	public Message handleMessage() {

		// Redirect to WhiteboardManager

		if (message.getType().contains("get-all-whiteboards")) {
			ArrayList<Whiteboard> allWhiteboards = WhiteboardManager
					.getAllWhiteboards();
			JsonArray allWhiteboardsToJson = parseArrayToJsonArray(allWhiteboards);
			message.setType("get-all-whiteboards");
			message.setData(allWhiteboardsToJson);
		}

		if (message.getType().contains("create-whiteboard")) {
			Whiteboard whiteboard = new Whiteboard();
			String name = message.getData().getJsonObject(0).getString("name");
			whiteboard.setName(name);
			WhiteboardManager wb = new WhiteboardManager();
			wb.addWhiteboard(whiteboard);
		}

		if (message.getType().contains("delete-whiteboard")) {
			WhiteboardManager wb = new WhiteboardManager();
			wb.deleteWhiteboard(message);
		}

		// Redirect to CurrentWhiteboardManager

		if (message.getType().contains("get-current-whiteboard")) {
			CurrentWhiteboardManager cwm = new CurrentWhiteboardManager(message
					.getData().getJsonObject(0).getString("name"));
		}

		// Always return a message

		return message;
	}

	private JsonArray parseArrayToJsonArray(ArrayList<Whiteboard> whiteboards) {

		JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();

		for (Whiteboard wb : whiteboards) {
			JsonObject whiteboard = Json.createObjectBuilder()
					.add("id", Integer.toString(wb.getId()))
					.add("name", wb.getName()).build();
			jsonArrayBuilder.add(whiteboard);
		}
		JsonArray jsonArray = jsonArrayBuilder.build();
		return jsonArray;
	}

	private JsonArray parseMessageToJson(Message message) {

		return null;
	}
}
