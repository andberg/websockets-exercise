package se.berglund.messagehandler;

import java.io.StringReader;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonValue;

import se.berglund.datastorage.CategoryManager;
import se.berglund.datastorage.CurrentWhiteboardManager;
import se.berglund.datastorage.PostitManager;
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

		// Redirect to WhiteboardManager, to get all whiteboards, create a
		// whiteboard or delete one.

		if (message.getType().contains("whiteboard")) {
			Whiteboard whiteboard = new Whiteboard();
			WhiteboardManager whiteboardManager = WhiteboardManager.getInstance(); 

			if (message.getType().contains("get-all-whiteboards")) {
				ArrayList<Whiteboard> allWhiteboards = whiteboardManager.getAllWhiteboards();
				JsonArray allWhiteboardsToJson = parseWhiteboardArrayToJsonArray(allWhiteboards);
				message.setData(allWhiteboardsToJson);
			}

			if (message.getType().contains("create-whiteboard")) {
				whiteboard.setName( message.getData().getJsonObject(0)
						.getString("name"));
				whiteboardManager.addWhiteboard(whiteboard);
			}

			if (message.getType().contains("delete-whiteboard")) {
				whiteboard.setId(message.getData().getJsonObject(0).getInt("id"));
				whiteboard.setName(message.getData().getJsonObject(0).getString("name"));
				whiteboardManager.deleteWhiteboard(whiteboard);
			}
		}

		// Redirect to CurrentWhiteboardManager where we get categories an postits for current whiteboard
		// First as arraylists and ten as json. 

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

		// Redirect to CategoryManager for full CRUD functionality

		if (message.getType().contains("category")) {

			Category category = new Category();
			CategoryManager categoryManager = new CategoryManager();

			if (message.getType().contains("create")) {
				category.setWhiteboardId(Integer.parseInt(message.getData()
						.getJsonObject(0).getString("whiteboard")));
			} else {
				category.setId(message.getData().getJsonObject(0).getInt("id"));
				category.setWhiteboardId(message.getData().getJsonObject(0)
						.getInt("whiteboard"));
			}
			category.setName(message.getData().getJsonObject(0)
					.getString("name"));

			ArrayList<Postit> postits = new ArrayList<Postit>();
			JsonArray jsonArray = message.getData().getJsonObject(0)
					.getJsonArray("postits");
			if (jsonArray != null) {
				for (JsonValue value : jsonArray) {
					JsonObject jsonObject = Json.createReader(
							new StringReader(value.toString())).readObject();
					Postit postit = new Postit();
					postit.setId(jsonObject.getInt("id"));
					postit.setCategoryId(jsonObject.getInt("category"));
					postit.setTitle(jsonObject.getString("title"));
					postit.setDescription(jsonObject.getString("description"));
					postit.setColor(jsonObject.getString("color"));

					postits.add(postit);
				}
			}

			category.setPostits(postits);

			if (message.getType().contains("update-category")) {
				categoryManager.updateCategory(category);
			}
			if (message.getType().contains("delete-category")) {
				categoryManager.deleteCategory(category);
			}
			if (message.getType().contains("create-category")) {
				categoryManager.createCategory(category);
			}
		}

		// Redirect to PostitManager for full CRUD functionality

		if (message.getType().contains("postit")) {
			PostitManager postitManager = new PostitManager();
			Postit postit = new Postit();
			if (message.getType().contains("create")) {
				postit.setWhiteboardId(Integer.parseInt(message.getData()
						.getJsonObject(0).getString("whiteboard")));
			} else {
				postit.setId(message.getData().getJsonObject(0).getInt("id"));
				postit.setWhiteboardId(message.getData().getJsonObject(0)
						.getInt("whiteboard"));
			}
			postit.setCategoryId(message.getData().getJsonObject(0)
					.getInt("category"));
			postit.setTitle(message.getData().getJsonObject(0)
					.getString("title"));
			postit.setDescription(message.getData().getJsonObject(0)
					.getString("description"));
			postit.setColor(message.getData().getJsonObject(0)
					.getString("color"));

			if (message.getType().contains("delete-postit")) {
				postitManager.deletePostit(postit);
			}

			if (message.getType().contains("update-postit")) {
				postitManager.updatePostit(postit);
			}

			if (message.getType().contains("create-postit")) {
				postitManager.createPostit(postit);
			}
		}

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
					.add("id", pos.getId()).add("title", pos.getTitle())
					.add("category", pos.getCategoryId())
					.add("color", pos.getColor())
					.add("whiteboard", pos.getWhiteboardId())
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
					.add("id", cat.getId()).add("name", cat.getName())
					.add("whiteboard", cat.getWhiteboardId())
					.add("postits", array).build();

			jsonArrayBuilder.add(category);
		}

		JsonArray jsonArray = jsonArrayBuilder.build();
		return jsonArray;

	}
}
