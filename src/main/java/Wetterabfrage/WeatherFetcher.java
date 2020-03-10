package Wetterabfrage;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class WeatherFetcher {

    private static WeatherFetcher instance;

    private WeatherFetcher(){

    }

    //Kann nur 1 WeatherFetcher geben:
    public static WeatherFetcher getInstance() {
        if(instance == null){
            instance = new WeatherFetcher();
        }
        return new WeatherFetcher();
    }

    public WeatherInfo[] fetch(String city) throws Exception {

        String uri = "https://api.openweathermap.org/data/2.5/forecast?q="+ city + "&mode=xml&appid=3e4c2347a419dc77f32574ca989cd747";
        //API einbinden mit Parser (DOM)
        //Create a DocumentBuilder:
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document document = documentBuilder.parse(uri);

        NodeList times = document.getElementsByTagName("time"); //Return a NodeList with Tag: <time>

        //times.item(0); //1. <time> Element

        WeatherInfo[] weatherInfos = new WeatherInfo[times.getLength()];

        //Iteriert durch die ganze NodeList
        for(int x = 0; x < times.getLength(); x++){
            //System.out.println("Test");
            Node time = times.item(x); //get all <time> Elements as Node
            NamedNodeMap timeAttributes = time.getAttributes(); //get all Attribute of <time> as Node
            String timestamp = timeAttributes.getNamedItem("from").getNodeValue(); //get Value of the node

            NodeList children = time.getChildNodes(); //A NodeList with all the Child Nodes of Time (times.item(x))
            for (int y = 0; y < children.getLength(); y++){
                Node child = children.item(y); //get all children Elements of <time>
                if (child.getNodeName() == "temperature"){
                    String temperature = child.getAttributes().getNamedItem("value").getNodeValue(); //get the value of the temperature
                    weatherInfos[x] = new WeatherInfo(timestamp, temperature);

                    //System.out.println(timestamp + ": " + temperature);
                }
            }
        }

        return weatherInfos;
        //System.out.println(times.getLength()); //37 Elements with <time>
        //System.out.println(document.getDocumentElement().getTagName());

    }

}
