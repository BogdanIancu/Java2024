package ro.ase.acs.xml;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="weather")
public class WeatherCondition {
    private String description;
    private float temperature;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }
}
