package ro.ase.acs.xml;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=Bucharest&appid=7b10426ee90376dc3d6525f847128b35&units=metric&lang=en&mode=xml");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream= connection.getInputStream();
            InputStreamReader inputStreamReader= new InputStreamReader(inputStream);


            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document xml = builder.parse(inputStream);
            Node temperatureNode = xml.getElementsByTagName("temperature").item(0);
            String temperature = temperatureNode.getAttributes().getNamedItem("value").getNodeValue();
            System.out.println("Temperature " + temperature);
            Node descriptionNode = xml.getElementsByTagName("weather").item(0);
            String description = descriptionNode.getAttributes().getNamedItem("value").getNodeValue();
            System.out.println("Description : "+ description);

            WeatherCondition weatherCondition = new WeatherCondition();
            weatherCondition.setDescription(description);
            weatherCondition.setTemperature(Float.parseFloat(temperature));
            JAXBContext context = JAXBContext.newInstance(WeatherCondition.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.marshal(weatherCondition, new File("file.xml"));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
