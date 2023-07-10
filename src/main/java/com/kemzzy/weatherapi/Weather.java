/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kemzzy.weatherapi;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;

/**
 *
 * @author kemzzy
 */
public class Weather{
    private String q;
    private String type;
    
    public Weather(String q, String type){
        this.q = q;
        this.type = type;
    }
    
    Object makeRequest(){
        Object obj = new Object();
        
        try{
            
            String Baseurl = "https://api.weatherapi.com/v1/";
            
            URL url = new URL(Baseurl + type + "?q=" + q );
            
            //A connection object to connect to the server and get the info
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("key", "abfd4f98746b4e88bdb110431230302");
            connection.setRequestProperty("Content-Type", "application/json");
    
            //Reading the returned data from the server
            if (connection.getResponseCode() == 200){
                Scanner scanner = new Scanner(url.openStream());
                String response = scanner.nextLine();
                
                JSONParser jsonparser = new JSONParser();
                obj = jsonparser.parse(response);
                
            }else{
                System.out.println("error");
            } 
        
        } catch (Exception e) {
                e.printStackTrace();
            }  
        return obj;
    }
    
    
    String getWeather(String args){
        
        Object obj = makeRequest();
        JSONObject jsonobj = (JSONObject)obj;
        
        Object current = jsonobj.get("current");
        JSONObject currentobj = (JSONObject)current; 
        
        Object condition = currentobj.get("condition");
        JSONObject conditionobj = (JSONObject)condition;
        
        String weather="";
        
        if (args == "text"){
            weather = (String) conditionobj.get("text");
        }else if(args == "icon"){
            String iconString = (String) conditionobj.get("icon");
            weather = "https:" + iconString;
        }else{
            System.out.println("Not applicable");
        }
        
        return weather;

    }
    
    String getTemperature(){
        Object obj = makeRequest();
        JSONObject jsonobj = (JSONObject)obj;
        
        Object current = jsonobj.get("current");
        JSONObject currentobj = (JSONObject)current;
        
        double me = (Double) currentobj.get("temp_c");
        
        
        return Double.toString(me);
    }
    
    String getWindSpeed(){
        Object obj = makeRequest();
        JSONObject jsonobj = (JSONObject)obj;
        
        Object current = jsonobj.get("current");
        JSONObject currentobj = (JSONObject)current; 
        
        double wind = (Double) currentobj.get("wind_kph");
        return Double.toString(wind);
    }
    
    String getHumidity(){
        Object obj = makeRequest();
        JSONObject jsonobj = (JSONObject)obj;
        
        Object current = jsonobj.get("current");
        JSONObject currentobj = (JSONObject)current; 
        
        long humidity = (Long) currentobj.get("humidity");
        return Double.toString(humidity);
    }
    
    String getPressure(){
        Object obj = makeRequest();
        JSONObject jsonobj = (JSONObject)obj;
        
        Object current = jsonobj.get("current");
        JSONObject currentobj = (JSONObject)current; 
        
        double pressure = (Double) currentobj.get("pressure_in");
        return Double.toString(pressure);
    }
    
    String getCurrentTime(){
        Object obj = makeRequest();
        JSONObject jsonobj = (JSONObject)obj;
        
        Object location = jsonobj.get("location");
        JSONObject locationobj = (JSONObject)location; 
        
        String localtime = (String) locationobj.get("localtime");
        String[] local = localtime.split(" ", 2);
        
        return local[1];
    }
}
