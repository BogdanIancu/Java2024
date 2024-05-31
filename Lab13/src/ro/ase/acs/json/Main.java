package ro.ase.acs.json;

import netscape.javascript.JSObject;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {
    public static void main(String[] args) {

        try {
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=Bucharest&appid=7b10426ee90376dc3d6525f847128b35&units=metric&lang=en");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream= connection.getInputStream();
            InputStreamReader inputStreamReader= new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = null;
            StringBuffer stringBuffer= new StringBuffer();
            while ((line =bufferedReader.readLine())!=null){

                stringBuffer.append(line);

            }

            String response = stringBuffer.toString();

            JSONObject jsonObject = new JSONObject(response);
            JSONObject mainObject = jsonObject.getJSONObject("main");
            int temperature = (int)mainObject.getFloat("temp");
            System.out.println("Temperature : " + temperature);
            JSONArray weatherArray = jsonObject.getJSONArray("weather");
            JSONObject weatherObject = weatherArray.getJSONObject(0);
            String description = weatherObject.getString("description");
            System.out.println("Conditions: " + description);


            JSONObject createdJson = new JSONObject();
            createdJson.put("temperature",temperature);
            createdJson.put("conditions",description);
            FileWriter fileWriter = new FileWriter("file.json");
            fileWriter.write(createdJson.toString());
            fileWriter.close();
        }

        catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}