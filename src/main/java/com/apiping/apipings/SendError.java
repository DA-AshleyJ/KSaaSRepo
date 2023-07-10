package com.apiping.apipings;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;


public class SendError {

    public void newWebHook(String exception, String ServiceName) throws IOException {
        URL url = new URL("https://"); // This is the webhook url needed for teams channel.
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.setRequestProperty("Accept", "application/json");
        http.setRequestProperty("Content-Type", "application/json");
        String data = "{\n    \"@type\": \"MessageCard\",\n    \"@context\": \"http://schema.org/extensions\",\n    \"themeColor\": \"0076D7\",\n    \"summary\": \"Error on"+ServiceName+"\",\n    \"sections\": [\n        {\n            \"activityTitle\": \"Error on "+ServiceName+"\",\n            \"activitySubtitle\": \"A component is not responding!\",\n            \"activityImage\": \"https://www.deviceauthority.com/wp-content/uploads/2021/08/logo.svg\",\n            \"facts\": [\n                {\n                    \"name\": \"HTTP Response Code\",\n                    \"value\": \""+exception+"\"\n                }\n            ],\n            \"markdown\": true\n        }\n    ]\n}";
        byte[] out = data.getBytes(StandardCharsets.UTF_8);
        OutputStream stream = http.getOutputStream();
        stream.write(out);
        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
    }
   /* public String createTicket(String exception, String Env) throws IOException {
            URL url = new URL("https://");
            HttpURLConnection http = (HttpURLConnection)url.openConnection();
            http.setRequestMethod("POST");
            http.setDoOutput(true);
            http.setRequestProperty("Accept", "application/json");
            http.setRequestProperty("Authorization", "");
            http.setRequestProperty("Content-Type", "application/json");
            String data = "{\n  \"ticket\": {\n    \"subject\": \"Ticket from AJ API\",\n    \"comment\": {\n      \"body\": \"The error from the application stated: " +exception+Env+", Please take a look at this as soon as possible.\"\n    }\n  }\n}";
            byte[] out = data.getBytes(StandardCharsets.UTF_8);
            OutputStream stream = http.getOutputStream();
            stream.write(out);
            http.disconnect();
            return "success";
        }*/
}
