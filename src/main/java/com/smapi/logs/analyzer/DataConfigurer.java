package com.smapi.logs.analyzer;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.HashMap;

import static com.smapi.logs.analyzer.Constants.INIT_DATA_FILE_PATH;
import static com.smapi.logs.analyzer.LogAnalyzer.baseService;

class DataConfigurer {

    static HashMap<String, Double> resultMap = new HashMap<>();

    void configureInitialDataSet() {
        JSONParser parser = new JSONParser();

        try {

            Object obj = parser.parse(new FileReader(INIT_DATA_FILE_PATH));
            JSONObject jsonObject = (JSONObject) obj;

            Object serviceObject = jsonObject.get(baseService);
            resultMap = (HashMap<String, Double>) serviceObject;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}