package example.eatme.comm.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;
import example.eatme.util.Constants;

public class LifeSumClient {

	public String Get(String url) {

		InputStream inputStream = null;
		String result = "";

		try {
			HttpClient httpclient = new DefaultHttpClient();

			HttpGet httpGet = new HttpGet(url);

			httpGet.addHeader("Authorization", Constants.ACCESS_TOKEN);
			httpGet.addHeader("Content-Type", "application/json");
			httpGet.addHeader("Accept", "application/json");

			HttpResponse httpResponse = httpclient.execute(httpGet);

			inputStream = httpResponse.getEntity().getContent();

			if (inputStream != null) {
				result = convertInputStreamToString(inputStream);
			} else {
				result = "Server response fail.";
			}
		} catch (Exception e) {
			Log.d("LifeSumClient", e.getLocalizedMessage());
		}

		return result;
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
