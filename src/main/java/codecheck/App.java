package codecheck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

public class App {
	public static void main(String[] args) {

		String uri = "http://challenge-server.code-check.io/api/hash?q=";

		String input = "fizz";

		StringBuilder builder = new StringBuilder();
		HttpClient client = new DefaultHttpClient();

		HttpGet httpGet = new HttpGet(uri + input);
		try {
			HttpResponse response = client.execute(httpGet);
			//返却されたHTTPレスポンスの中のステータスコードを調べる
			// -> statusCodeが200だったらページが存在。404だったらNot found（ページが存在しない）。500はInternal server error。
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode == 200) {
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(new InputStreamReader(content));
				String line;
				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}
			} else {
				System.out.println("Failed to download file");
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 文字列をJSONオブジェクトに変換する
		try {
			JSONArray jsonArray = new JSONArray(builder.toString());
			System.out.println("Number of entries " + jsonArray.length());
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
                                System.out.println(i);
				System.out.println("入力値："+jsonObject.getString("q"));
				System.out.println("ハッシュ値："+jsonObject.getString("hash"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
