package com.smapi.logs.analyzer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.smapi.logs.analyzer.Constants.LOGS_FILE_PATH;

class ServiceUsagePercentageCalculator {

    double calculateServiceUsage(String serviceName,
                                 int occurrenceCount, int totalOccurrences) throws IOException {

        FileInputStream fileInputStream = new FileInputStream(LOGS_FILE_PATH);
        BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));
        TypeUsageCalCurator tuc = new TypeUsageCalCurator();
        TotalUsageCalculator touc = new TotalUsageCalculator();

        String strLine;
        int serviceCount = 0;
        int repoCount = 0;
        int managerCount = 0;
        int controllerCount = 0;

        while ((strLine = br.readLine()) != null)   {

            if (strLine.contains("SMAPI") && strLine.contains(serviceName)) {
                if (strLine.contains("Controller")) {
                    controllerCount += 1;
                } else if (strLine.contains("Service")) {
                    serviceCount += 1;
                } else if (strLine.contains("Manager")) {
                    managerCount += 1;
                } else if (strLine.contains("Repository")) {
                    repoCount += 1;
                }
            }
        }

        System.out.println("--------"+serviceName+"--------");
        System.out.println("-----------------------------------");
        System.out.println("Service Count : " + serviceCount);
        System.out.println("Repo Count : " + (repoCount + serviceCount));
        System.out.println("Manager Count : " + managerCount);
        System.out.println("Controller Count : " + controllerCount);
        System.out.println("-----------------------------------");

        double controllerUsage= 0.00, serviceUsage= 0.00, managerUsage= 0.00, repoUsage = 0.00;

        if (controllerCount > 0) {
            controllerUsage = tuc.calculateControllerUsage(controllerCount, serviceName);
        }

        if (serviceCount > 0) {
            serviceUsage = tuc.calculateServiceUsage(serviceCount, serviceName);
        }

        if (managerCount > 0) {
            managerUsage = tuc.calculateManagerUsage(managerCount, serviceName);
        }

        if (repoCount > 0) {
            repoUsage = tuc.calculateRepoUsage(repoCount, serviceName);
        }

         return  tuc.calculateWholeServiceUsage(serviceName, controllerUsage, serviceUsage, managerUsage, repoUsage);
    }
}
