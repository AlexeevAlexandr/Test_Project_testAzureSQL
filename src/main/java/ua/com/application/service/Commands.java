package ua.com.application.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import ua.com.application.dao.OrdersDAO;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.Objects;
import java.util.Scanner;

public class Commands {

    private String getLink() {
        try (Scanner scanner = new Scanner(new FileReader("src/main/resources/file/linkToJsonData.csv"))) {
            return scanner.nextLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private URL getUrlConnect() {
        Authenticator.setDefault(new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("admin@max69","61ae20975e".toCharArray());
            }
        });

        URL url = null;
        try {
            url = new URL(Objects.requireNonNull(getLink()));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int response = conn.getResponseCode();
            if (response != 200) {
                throw new RuntimeException("HttpResponseCode: " + response);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return url;
    }

    private String getDataThroughUrl(){
        StringBuilder stringBuilder = new StringBuilder();
        try (Scanner sc = new Scanner(getUrlConnect().openStream())){
            while (sc.hasNext()) {
                stringBuilder.append(sc.nextLine());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    private JSONArray getJSONArray(){
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(getDataThroughUrl());
            return (JSONArray) jsonObject.get("rows");
        }catch (ParseException e){
            e.printStackTrace();
        }
        return null;
    }

    public void checkNewOrders() {
        OrdersDAO ordersDAO = new OrdersDAO();
        int lastOrderNameFromDatabase = ordersDAO.getLastNameOfOrderFromDatabase();

        Commands command = new Commands();
        JSONArray jsonArray =  command.getJSONArray();

        int counterAddedOrders = 0;
        for(Object object : Objects.requireNonNull(jsonArray)) {
            JSONObject jsonObject = (JSONObject) object;

            int jsonOrderName = Integer.parseInt(jsonObject.get("name").toString());

            if (jsonOrderName > lastOrderNameFromDatabase) {
                String id = jsonObject.get("id").toString();
                double sum = Double.parseDouble(jsonObject.get("sum").toString());
                String moment = jsonObject.get("moment").toString();
                String name = jsonObject.get("name").toString();
                String description = "";  //TODO left an end for future descriptions

                //get counterparty_uuid
                JSONObject agent = (JSONObject) jsonObject.get("agent");
                JSONObject meta = (JSONObject) agent.get("meta");
                String uuidHref = meta.get("uuidHref").toString();
                String[] separatesCounterparty_uuid = uuidHref.split("=");
                String counterparty_uuid = separatesCounterparty_uuid[1];

                ordersDAO.writeOrders(id, name, description, sum, counterparty_uuid, moment);
                counterAddedOrders++;
            }
        }
        System.out.println((counterAddedOrders == 0) ? "No new orders" : "You have " + counterAddedOrders + " new orders");
    }
}
