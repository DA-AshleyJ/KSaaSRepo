package com.apiping.apipings;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import org.slf4j.Logger;

public class APIPings {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(APIPings.class);
    public static void Sac() throws IOException {
        SendError SE1 = new SendError();
        URL sac = new URL("https://");
        HttpURLConnection conn = (HttpURLConnection) sac.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        //Getting the response code
        int responsecode = conn.getResponseCode();
        String myString = Integer.toString(responsecode);
        if (responsecode != 200) {
            SE1.newWebHook(myString, "SAC");
            logger.error("SAC Failing");
            conn.disconnect();
            return;
        }
       else {
            conn.disconnect();
            logger.info("SAC OK");
        }
        return;
    }

    public static void DAE() throws IOException {
        SendError SE2 = new SendError();
        URL dae = new URL("https://");
        HttpURLConnection conn = (HttpURLConnection) dae.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        //Getting the response code
        int responsecode2 = conn.getResponseCode();
        String myString = Integer.toString(responsecode2);
        if (responsecode2 != 200) {
            SE2.newWebHook(myString, "DAE");
            logger.error("DAE Failing");
            conn.disconnect();
            return;
        }
       else {
            conn.disconnect();
            logger.info("DAE OK");
        }
        return;
    }

    public static void KMS() throws IOException {
        SendError SE3 = new SendError();
        URL kms = new URL("https://");
        HttpURLConnection conn = (HttpURLConnection) kms.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        //Getting the response code
        int responsecode3 = conn.getResponseCode();
        String myString = Integer.toString(responsecode3);
        if (responsecode3 != 200) {
            SE3.newWebHook(myString, "KMS");
            logger.error("KMS Failing");
            conn.disconnect();
            return;
        }
        else {
            conn.disconnect();
            logger.info("KMS OK");
        }
       return;
    }

    public static void CP() throws IOException {
        SendError SE4 = new SendError();
        URL cp = new URL("https://");
        HttpURLConnection conn = (HttpURLConnection) cp.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        //Getting the response code
        int responsecode4 = conn.getResponseCode();
        String myString = Integer.toString(responsecode4);
        if (responsecode4 != 200) {
            SE4.newWebHook(myString, "CP");
            logger.error("CP Failing");
            conn.disconnect();
        }
        else {
            conn.disconnect();
            logger.info("CP OK");
        }
        return;
    }
}


