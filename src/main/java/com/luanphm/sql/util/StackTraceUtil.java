package com.luanphm.sql.util;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2021.10.29 11:01
 */
public class StackTraceUtil {

    private static List<String> skippedMethods = Arrays.asList(
        "getStackTrace",
        "printStackTrace"
    );

    public static void printStackTrace(String module) {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        System.out.printf("<-------------------- %s -------------------->%n", module.toUpperCase());
        for (StackTraceElement stackTraceElement : stackTraceElements) {
            if (skippedMethods.contains(stackTraceElement.getMethodName())) continue;
            System.out.printf("%s.%s:%s%n", stackTraceElement.getClassName(), stackTraceElement.getMethodName(), stackTraceElement.getLineNumber());
        }
        System.out.printf("</-------------------- %s -------------------->%n", module.toUpperCase());
    }

}
