package com.smapi.logs.analyzer;

import java.util.HashMap;

import static com.smapi.logs.analyzer.Constants.BASE_VALUE;
import static com.smapi.logs.analyzer.DataSet.*;
import static com.smapi.logs.analyzer.DataConfigurer.resultMap;

class TypeUsageCalCurator {

    static Double coarseValue = 0.00;
    static Double grainValue = 0.00;
    static HashMap<String, Integer> occurrences;
    static Integer total;

    double calculateServiceUsage(int serviceCount, String serviceName) {

        return (double)serviceCount/servicesMap.get(serviceName);
    }

    double calculateManagerUsage(int managerCount, String serviceName) {

        return (double)managerCount/managersMap.get(serviceName);
    }

    double calculateRepoUsage(int repoCount, String serviceName) {

        return (double)repoCount/reposMap.get(serviceName);
    }

    double calculateWholeServiceUsage(String serviceName, double serviceUsage, double managerUsage, double repoUsage) {


        ValueCleanUpper valueCleanUpper = new ValueCleanUpper();
        Double calculatedValue;

        if (resultMap.get(serviceName) != null) {
            coarseValue = Double.valueOf(String.valueOf(resultMap.get(serviceName)));
        } else {
            coarseValue = BASE_VALUE;
        }

        calculatedValue = (serviceUsage + managerUsage + repoUsage)/3;
        grainValue = calculatedValue;

        return calculatedValue > 0 ? valueCleanUpper.cleanUpFinalValue(calculatedValue) : coarseValue;
    }

    public void updateOccurrencesToProcess(HashMap<String, Integer> occurrences, Integer total) {
        this.occurrences = occurrences;
        this.total=total;
    }
}
