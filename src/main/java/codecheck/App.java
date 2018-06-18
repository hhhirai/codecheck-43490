package codecheck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;



public class App {
	public static void main(String[] args) {

		URL url;
		for (int i = 0, l = args.length; i < l; i++) {
			String input = args[i];

		try {
			url = new URL("http://challenge-server.code-check.io/api/hash?q=" + input);
			StringBuilder builder = new StringBuilder();

			HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {

                try (InputStreamReader isr = new InputStreamReader(connection.getInputStream(),
                                                                   StandardCharsets.UTF_8);
                    BufferedReader reader = new BufferedReader(isr)) {
                    String line = "";
                    while ((line = reader.readLine()) != null) {
                    	builder.append(line);
                    }
                }
            }

         } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        } catch (IOException e) {
        e.printStackTrace();
        }

		try {
			JSONArray jsonArray = new JSONArray(builder.toString());
			System.out.println("Number of entries " + jsonArray.length());
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				System.out.println("投稿日："+jsonObject.getString("hash"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	}

}
