package com.smapi.logs.analyzer;

import static com.smapi.logs.analyzer.DataSet.*;
import static com.smapi.logs.analyzer.TotalUsageCalculator.resultMap;

class TypeUsageCalCurator {


    double calculateControllerUsage(int controllerCount, String serviceName) {

        return (double)controllerCount/controllersMap.get(serviceName);
    }

    double calculateServiceUsage(int serviceCount, String serviceName) {

        return (double)serviceCount/servicesMap.get(serviceName);
    }

    double calculateManagerUsage(int managerCount, String serviceName) {

        return (double)managerCount/managersMap.get(serviceName);
    }

    double calculateRepoUsage(int repoCount, String serviceName) {

        return (double)repoCount/reposMap.get(serviceName);
    }

    double calculateWholeServiceUsage(String serviceName, double controllerUsage, double serviceUsage,
                                              double managerUsage, double repoUsage) {

        Double finalValue = 0.00;

        if (resultMap.get(serviceName) != null) {
            finalValue = Double.valueOf(String.valueOf(resultMap.get(serviceName)));
        }


        //(controllerUsage + serviceUsage + managerUsage + repoUsage)/4

        return finalValue;
    }
}
