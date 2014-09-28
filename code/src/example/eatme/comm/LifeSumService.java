package example.eatme.comm;

import java.security.InvalidParameterException;
import java.util.ArrayList;

import org.json.JSONException;

import android.content.Context;
import android.util.Log;
import example.eatme.comm.mock.LifeSumClientMock;
import example.eatme.models.Food;
import example.eatme.util.Constants;
import example.eatme.util.FoodParser;

public class LifeSumService {

	private Context context;
	
	public LifeSumService(Context context) {
		this.context = context;
	}
	
	public ArrayList<Food> getFoods(String filter) throws InvalidParameterException {
		
		LifeSumClientMock lifeSumClient = new LifeSumClientMock(this.context);

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
