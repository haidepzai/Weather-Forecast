package Wetterabfrage;

public class WeatherInfo {

    private String timestamp;
    private String temperature;

    public WeatherInfo(String timestamp, String temperature){
        this.timestamp = timestamp;
        this.temperature = temperature;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public static float celsiusCasting(String kelvinTemperature) {
        float kelvin = Float.parseFloat(kelvinTemperature);
        float celsius = kelvin - 273;
        return celsius;
    }


}
