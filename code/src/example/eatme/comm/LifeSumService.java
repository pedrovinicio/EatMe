package example.eatme.comm;

import java.security.InvalidParameterException;
import java.util.ArrayList;

import org.json.JSONException;

import android.util.Log;
import example.eatme.comm.client.LifeSumClient;
import example.eatme.models.Food;
import example.eatme.util.Constants;
import example.eatme.util.FoodParser;

public class LifeSumService {

	public ArrayList<Food> getFoods(String filter) throws InvalidParameterException {
		
		LifeSumClient lifeSumClient = new LifeSumClient();

		if (filter == null || filter.equals("")) {
			throw new InvalidParameterException("filter: " + filter);
		}

		String query = String.format(Constants.SEARCH_QUERY, filter);
		String url = Constants.URL + query;

		String result = lifeSumClient.Get(url);

		try {
			return FoodParser.Parser(result);
		} catch (JSONException e) {
			Log.d("LifeSumService", e.getLocalizedMessage());
		}
		return null;
	}

}
