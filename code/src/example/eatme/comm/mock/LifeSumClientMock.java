package example.eatme.comm.mock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import example.eatme.R;

import android.content.Context;
import android.util.Log;

public class LifeSumClientMock {
	
	private Context context;
	
	public LifeSumClientMock(Context context) {
		this.context = context;
	}
	
	public String Get(String url) {

		InputStream in = this.context.getResources().openRawResource(R.raw.server_response);
		
		String responseAsString = null;
		
		try {
			responseAsString = this.convertInputStreamToString(in);
		} catch (IOException e) {
			Log.d("LifeSumClient", e.getLocalizedMessage());
		}
		
		return responseAsString;
	}
	
	private String convertInputStreamToString(InputStream inputStream)
			throws IOException {
		
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(inputStream));

		String line = "";
		String result = "";

		while ((line = bufferedReader.readLine()) != null) {
			result += line;
		}

		inputStream.close();

		return result;

	}
}
