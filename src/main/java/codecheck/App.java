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
		try {
			url = new URL("http://challenge-server.code-check.io/api/hash?p=" + args);

        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int result = connection.getResponseCode();
            System.out.println(result);

//            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
//                try (InputStreamReader isr = new InputStreamReader(connection.getInputStream(),
//                                                                   StandardCharsets.UTF_8);
//                    BufferedReader reader = new BufferedReader(isr)) {
//                    String line = "あ";
//                    while ((line = reader.readLine()) != null) {
//                        System.out.println(line);
//                    }
//                }
//            }
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
