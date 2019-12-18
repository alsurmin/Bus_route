package com.example.filedemo.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Service
@Scope("singleton")
public class BusRoutService {

    String to;
    String from;

    public BusRoutService(String to, String from) {
        this.to = to;
        this.from = from;
    }

    public BusRoutService() {
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String parseFile(MultipartFile multipartFile, String from, String to) {

        String result = "-1";

        try {
            String line;

            InputStreamReader inputStreamReader = new InputStreamReader(multipartFile.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            while((line = bufferedReader.readLine()) != null ) {

                line = line.trim();

                List<String> listOfStations = Arrays.asList(line.split(" "));
                String numberOfRoute = listOfStations.get(0);
                listOfStations.remove(new Integer(0));

                int indexFrom = listOfStations.indexOf(from);
                int indexTo = listOfStations.indexOf(to);

                if (indexFrom != -1 && indexTo != -1) {
                    if (indexFrom > indexTo) {
                        result = numberOfRoute;
                        break;
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;

    }
}
