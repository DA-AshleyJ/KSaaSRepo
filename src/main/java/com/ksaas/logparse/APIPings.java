package com.ksaas.logparse;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class APIPings {

    public static void Sac() throws IOException {
        SendError SE = new SendError();
        URL url = new URL("https://keyscalerdemo.sac.keyscaler.com/service-access-controller/health/ping");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        //Getting the response code
        int responsecode = conn.getResponseCode();
        String myString = Integer.toString(responsecode);
        if (responsecode != 200) {
            SE.newWebHook(myString, "SAC");
            conn.disconnect();
            return;
        }
        if (responsecode == 200){
            conn.disconnect();
            return;
        }
        conn.disconnect();
        DAE();
        return;
    }
    public static void DAE() throws IOException {
        SendError SE = new SendError();
        URL url = new URL("https://keyscalerdemo.sac.keyscaler.com/service/api/health/ping");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        //Getting the response code
        int responsecode = conn.getResponseCode();
        String myString = Integer.toString(responsecode);
        if (responsecode != 200) {
            SE.newWebHook(myString, "DAE");
            conn.disconnect();
            return;
        }
        if (responsecode == 200){
            conn.disconnect();
            return;
        }
        conn.disconnect();
        CP();
        return;
    }
/*    public static void KMS() throws IOException {
        SendError SE = new SendError();
        URL url = new URL("https://keyscalerdemo.sac.keyscaler.com/service-access-controller/health/ping");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        //Getting the response code
        int responsecode = conn.getResponseCode();
        String myString = Integer.toString(responsecode);
        if (responsecode != 200) {
            SE.newWebHook(myString, "KMS");
            conn.disconnect();
            return;
        }
        conn.disconnect();
        CP();
        return;
    }*/
    public static void CP() throws IOException {
        SendError SE = new SendError();
        URL url = new URL("https://keyscalerdemo.cp.keyscaler.com/cp/api/health/ping");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        //Getting the response code
        int responsecode = conn.getResponseCode();
        String myString = Integer.toString(responsecode);
        if (responsecode != 200) {
            SE.newWebHook(myString, "CP");
            conn.disconnect();
            return;
        }
        if (responsecode == 200){
            conn.disconnect();
            
            return;
        }
        conn.disconnect();
        return;
    }
}


