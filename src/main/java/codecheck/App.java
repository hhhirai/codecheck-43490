package jp.co.jal.jalcard.myjalcard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class App {
	public static void main(String[] args) {

		URL uri;
		try {
			uri = new URL("http://challenge-server.code-check.io/api/hash?p=fizz");

        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) uri.openConnection();
            connection.setRequestMethod("GET");

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                try (InputStreamReader isr = new InputStreamReader(connection.getInputStream(),
                                                                   StandardCharsets.UTF_8);
                     BufferedReader reader = new BufferedReader(isr)) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
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
	}

}
