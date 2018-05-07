package com.smapi.logs.analyzer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class LogAnalyzer {

    public static void main(String[] args) {

        try{

            HashMap<String, Integer> occurrences = new HashMap<>();
            Integer total  = 0;

            FileInputStream fileInputStream = new FileInputStream("/Users/Chamikabandara/Projects/MyProjects/StudentManagementRestApi/app.log");
            BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));
            String strLine;

            while ((strLine = br.readLine()) != null)   {

                if (strLine.contains("SMAPI")) {

                    String[] data = strLine.split("SMAPI");
                    String[] possibleKeys = data[1].split("-");

                    String key = possibleKeys[1].trim();

                    if(occurrences.containsKey(key)) {
                        occurrences.put(key, occurrences.get(key)+1);
                    } else {
                        occurrences.put(key, 1);
                    }

                    total +=1;
                }
            }

            printTable(getPercentages(occurrences, total));

            fileInputStream.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static HashMap<String, String> getPercentages(HashMap<String, Integer> occurrences, Integer total) {

        HashMap<String, String> finalResultMap = new HashMap<>();

        for (String key : occurrences.keySet()) {
            double percentage = (occurrences.get(key)/(double)total) * 100;
            finalResultMap.put(key, String.format("%.2f", percentage) + " %");
        }

        return finalResultMap;
    }

    private static void printTable(HashMap<String, String> precentageValues) {
        System.out.println("-----------------------------------");
        System.out.format("%15s %14s", "Service Name", "Percentage");
        System.out.println();
        System.out.println("-----------------------------------");

        for (String key : precentageValues.keySet()) {
            System.out.format("%15s %10s", key, precentageValues.get(key));
            System.out.println();
        }
        System.out.println("-----------------------------------");
    }

}
