package example.eatme.util;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import example.eatme.models.Food;

public class FoodParser {

	public static ArrayList<Food> Parser(String content) throws JSONException {

		JSONObject reader = new JSONObject(content);

		reader = reader.getJSONObject("response");

		JSONArray jsonArray = reader.getJSONArray("list");

		int size = jsonArray.length();

		ArrayList<Food> foods = new ArrayList<Food>();

		for (int i = 0; i < size; i++) {
			JSONObject elem = jsonArray.getJSONObject(i);

			Food food = new Food();

			food.setTitle(elem.getString("title"));
			food.setCategory(elem.getString("category"));
			food.setBrand(elem.getString("brand"));

			food.setCalories((float) elem.getDouble("calories"));
			food.setCholesterol((float) elem.getDouble("cholesterol"));
			food.setCarbohydrates((float) elem.getDouble("carbohydrates"));
			food.setSugar((float) elem.getDouble("sugar"));
			food.setProtein((float) elem.getDouble("protein"));
			food.setFat((float) elem.getDouble("fat"));

			foods.add(food);
		}

		return foods;
	}
}
