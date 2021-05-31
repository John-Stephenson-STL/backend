package io.john.stephenson.stl.backend.controllers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlChecklController {
    private final String SITE_IS_UP = "Site is up!";
    private final String SITE_IS_DOWN = "Site is down!";
    private final String INCORRECT_URL = "Incorrect URL!";

    @GetMapping("/check")
    public String getUrlStatusMesasge(@RequestParam String url) {
        String returnMessage = "";
        try {
            URL urlObj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCodeCategory = conn.getResponseCode() / 100;
            if((responseCodeCategory == 2 ) || (responseCodeCategory == 3)) {
                returnMessage = SITE_IS_UP;
            } else {
                returnMessage = SITE_IS_DOWN;
            }
        } catch (MalformedURLException e) {
            returnMessage = INCORRECT_URL;
            e.printStackTrace();
        } catch (IOException e) {
            returnMessage = SITE_IS_DOWN;
            e.printStackTrace();
        }
        return returnMessage;
    }
}