import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import task2.Database;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class testPDL {

    @Test
    public void testPDL() throws IOException {
        Database db = new Database();
        if (db.get("github.com").equals("not found")) {
            String API_KEY = "03f8d36d258ec253eea3cf2de09d45ad085fcf7e489748adcd479e46f261737e";
            String query = URLEncoder.encode("SELECT NAME FROM COMPANY WHERE WEBSITE='github.com'", StandardCharsets.UTF_8);
            URL url = new URL("https://api.peopledatalabs.com/v5/company/search?sql=" + query);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("X-Api-Key", API_KEY);
            connection.connect();
            String text = new Scanner(connection.getInputStream()).useDelimiter("\\Z").next();
            JSONObject jsonObject = new JSONObject(text);
            db.add("github.com", jsonObject);
            System.out.println(jsonObject);
        }
        else {
            System.out.println(db.get("github.com"));
        }
    }
}