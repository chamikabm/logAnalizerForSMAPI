package com.smapi.logs.analyzer;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.HashMap;

import static com.smapi.logs.analyzer.LogAnalyzer.baseService;

class TotalUsageCalculator {

    public static HashMap<String, Double> resultMap = new HashMap<>();

    void calculateTotalUsage() {
        JSONParser parser = new JSONParser();

        try {

            Object obj = parser.parse(new FileReader("/Users/Chamikabandara/Projects/MyProjects/ProjectResources/LogData/info.json"));
            JSONObject jsonObject = (JSONObject) obj;

            Object serviceObject = jsonObject.get(baseService);
            resultMap = (HashMap<String, Double>) serviceObject;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}