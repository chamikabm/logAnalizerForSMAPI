package com.smapi.logs.analyzer;

import static com.smapi.logs.analyzer.TypeUsageCalCurator.coarseValue;
import static com.smapi.logs.analyzer.TypeUsageCalCurator.grainValue;

class ValueCleanUpper {

    double cleanUpFinalValue (Double valueToCleanUp) {

        return  (valueToCleanUp/grainValue)*coarseValue;
    }
}
