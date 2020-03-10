package de.hdm_stuttgart.mi.sd1;

import java.util.Scanner;

import Wetterabfrage.WeatherFetcher;
import Wetterabfrage.WeatherInfo;

public class Main {
	
	/*public static float celsiusCasting(String kelvinTemperature) {
	    float kelvin = Float.parseFloat(kelvinTemperature);
	    float celsius = kelvin - 273;
	    return celsius;
	}*/

    public static void main(String[] args) throws Exception {

        System.out.println("Für welche Stadt soll das Wetter abgefragt werden?");
        Scanner input = new Scanner(System.in);
        String city = input.next();

        WeatherFetcher weatherFetcher = WeatherFetcher.getInstance();

        WeatherInfo[] weatherInfos = weatherFetcher.fetch(city);

        for(int i = 0; i < weatherInfos.length; i++){
            WeatherInfo weatherInfo = weatherInfos[i];
            System.out.println(weatherInfo.getTimestamp() +" : " + WeatherInfo.celsiusCasting(weatherInfo.getTemperature() ) + " C°");
        }

        input.close();

    }

}
