package com.smapi.logs.analyzer;

import java.util.HashMap;

class DataSet {

    static HashMap<String, Integer> servicesMap = new HashMap<>();
    static HashMap<String, Integer> reposMap = new HashMap<>();
    static HashMap<String, Integer> managersMap = new HashMap<>();

    void initDataSet () {

        DataConfigurer dataConfigurer = new DataConfigurer();
        dataConfigurer.configureInitialDataSet();

        servicesMap.put("Student", 12);
        servicesMap.put("Lecturer", 8);
        servicesMap.put("Department", 6);
        servicesMap.put("Payment", 6);
        servicesMap.put("Exam", 7);
        servicesMap.put("Registration", 10);

        reposMap.put("Student", 6);
        reposMap.put("Lecturer", 6);
        reposMap.put("Department", 6);
        reposMap.put("Payment", 6);
        reposMap.put("Exam", 6);
        reposMap.put("Registration", 6);

        managersMap.put("Student", 6);
        managersMap.put("Lecturer", 6);
        managersMap.put("Department", 5);
        managersMap.put("Payment", 4);
        managersMap.put("Exam", 4);
        managersMap.put("Registration", 7);
    }
}
