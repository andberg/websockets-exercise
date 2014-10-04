package se.berglund.messagehandlers;

import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonValue;

import se.berglund.datastorage.CurrentWhiteboardManager;
import se.berglund.datastorage.WhiteboardManager;
import se.berglund.models.Category;
import se.berglund.models.Message;
import se.berglund.models.Postit;
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
			JsonArray allWhiteboardsToJson = parseWhiteboardArrayToJsonArray(allWhiteboards);
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
			CurrentWhiteboardManager cwm = new CurrentWhiteboardManager(
					Integer.parseInt(message.getData().getJsonObject(0)
							.getString("id")));

			ArrayList<Category> categoriesForWhiteboard = cwm
					.getAllCategoriesForCurrentWhiteboard();

			ArrayList<Postit> postitsForWhitaboard = cwm
					.getAllPostitsForCurrentWhiteboard();
			
			JsonArray jsonCategories = parseCategoryArrayToJsonArray(categoriesForWhiteboard);
			JsonArray jsonPostits = parsePostitArrayToJsonArray(postitsForWhitaboard);
			
			JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
			jsonArrayBuilder.add(jsonCategories).add(jsonPostits);
			
			JsonArray categoriesAndPostits = jsonArrayBuilder.build(); 
			message.setData(categoriesAndPostits);

		}

		// Always return a message

		return message;
	}

	private JsonArray parseWhiteboardArrayToJsonArray(
			ArrayList<Whiteboard> whiteboards) {

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

	private JsonArray parsePostitArrayToJsonArray(ArrayList<Postit> postits) {

		JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();

		for (Postit pos : postits) {
			JsonObject postit = Json.createObjectBuilder()
					.add("id", pos.getId())
					.add("title", pos.getTitle())
					.add("category", pos.getCategoryId())
					.add("color", pos.getColor())
					.add("description", pos.getDescription()).build();
			jsonArrayBuilder.add(postit);
		}

		JsonArray jsonArray = jsonArrayBuilder.build();
		return jsonArray;
	}

	private JsonArray parseCategoryArrayToJsonArray(
			ArrayList<Category> categories) {

		JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();

		for (Category cat : categories) {
			JsonArray array = Json.createArrayBuilder().build();
			JsonObject category = Json.createObjectBuilder()
					.add("id", cat.getId())
					.add("name", cat.getName())
					.add("postits", array).build();

			jsonArrayBuilder.add(category);
		}

		JsonArray jsonArray = jsonArrayBuilder.build();
		return jsonArray;

	}
}
