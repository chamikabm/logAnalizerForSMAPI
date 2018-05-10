package com.smapi.logs.analyzer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Scanner;

import static com.smapi.logs.analyzer.Constants.BASE_SERVICE_PERCENTAGE;
import static com.smapi.logs.analyzer.Constants.LOGS_FILE_PATH;

public class LogAnalyzer {

    public static String baseService;

    public static void main(String[] args) {

        System.out.println("Please select the Number of the Service that you used : ");
        HashMap<Integer ,String > servicesMap = getServicesMap();

        for (Integer key : servicesMap.keySet()) {
            System.out.println(key + "." + servicesMap.get(key));
        }

        System.out.println();
        Scanner scanner = new Scanner(System.in);
        Integer selectedService = scanner.nextInt();
        System.out.println();
        baseService = servicesMap.get(selectedService);
        System.out.println("End result will be based on  " + baseService  + " Service.");
        System.out.println();

        DataSet dataSet = new DataSet();
        dataSet.initDataSet();

        try{

            HashMap<String, Integer> occurrences = new HashMap<>();
            Integer total  = 0;

            FileInputStream fileInputStream = new FileInputStream(LOGS_FILE_PATH);
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

    private static HashMap<String, String> getPercentages(HashMap<String, Integer> occurrences, Integer total)
            throws IOException {

        HashMap<String, String> finalResultMap = new HashMap<>();
        ServiceUsagePercentageCalculator supc = new ServiceUsagePercentageCalculator();
        double percentage;

        for (String key : occurrences.keySet()) {

            if (key.equalsIgnoreCase(baseService)) {
                percentage = BASE_SERVICE_PERCENTAGE;
            } else {
                percentage = supc.calculateServiceUsage(key, occurrences.get(key), total);
                System.out.println("///////////////////////////////////");
            }

            finalResultMap.put(key, String.format("%.2f", percentage) + " %");
        }

        return finalResultMap;
    }

    private static void printTable(HashMap<String, String> percentageValues) {
        System.out.println("-----------------------------------");
        System.out.format("%15s %14s", "Service Name", "Percentage");
        System.out.println();
        System.out.println("-----------------------------------");

        for (String key : percentageValues.keySet()) {
            System.out.format("%15s %12s", key, percentageValues.get(key));
            System.out.println();
        }
        System.out.println("-----------------------------------");
    }

    private static HashMap<Integer ,String> getServicesMap() {

        HashMap<Integer ,String > servicesMap = new HashMap<>();
        servicesMap.put(1, "Student");
        servicesMap.put(2, "Lecturer");
        servicesMap.put(3, "Department");
        servicesMap.put(4, "Payment");
        servicesMap.put(5, "Exam");
        servicesMap.put(6, "Registration");

        return servicesMap;
    }

}
