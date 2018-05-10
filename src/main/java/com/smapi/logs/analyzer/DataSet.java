package com.smapi.logs.analyzer;

import java.util.HashMap;

public class DataSet {

    static HashMap<String, Integer> controllersMap = new HashMap<>();
    static HashMap<String, Integer> servicesMap = new HashMap<>();
    static HashMap<String, Integer> reposMap = new HashMap<>();
    static HashMap<String, Integer> managersMap = new HashMap<>();

    void initDataSet () {

        TotalUsageCalculator totalUsageCalculator = new TotalUsageCalculator();
        totalUsageCalculator.calculateTotalUsage();

        controllersMap.put("Student", 6);
        controllersMap.put("Lecturer", 6);
        controllersMap.put("Department", 6);
        controllersMap.put("Payment", 6);
        controllersMap.put("Exam", 6);
        controllersMap.put("Registration", 6);

        servicesMap.put("Student", 6);
        servicesMap.put("Lecturer", 6);
        servicesMap.put("Department", 6);
        servicesMap.put("Payment", 6);
        servicesMap.put("Exam", 6);
        servicesMap.put("Registration", 6);

        reposMap.put("Student", 6);
        reposMap.put("Lecturer", 6);
        reposMap.put("Department", 6);
        reposMap.put("Payment", 6);
        reposMap.put("Exam", 6);
        reposMap.put("Registration", 6);

        managersMap.put("Student", 6);
        managersMap.put("Lecturer", 6);
        managersMap.put("Department", 6);
        managersMap.put("Payment", 6);
        managersMap.put("Exam", 6);
        managersMap.put("Registration", 6);
    }


}
