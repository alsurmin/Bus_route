package com.example.filedemo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Service
public class BusRoutService {

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

                if (indexFrom != -1 && indexTo != 1) {
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
