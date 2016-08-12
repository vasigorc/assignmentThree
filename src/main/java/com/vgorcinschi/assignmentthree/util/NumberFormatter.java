/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vgorcinschi.assignmentthree.util;

import java.util.Scanner;
import java.util.function.Predicate;
import java.util.function.Supplier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author v_gorcin
 */
public class NumberFormatter {

    private static final Logger log = LogManager.getLogger();

    public static Double validateDoubleValue(Supplier<String> type,
            Scanner sc) {
        boolean responseReady = false;
        double response = 0;
        System.out.println("Please enter the " + type.get() + " value:");
        while (responseReady == false) {
            try {
                String unsanitized = sc.nextLine();
                response = Double.parseDouble(unsanitized);
                responseReady = true;
            } catch (NumberFormatException | NullPointerException e) {
                log.error("An invalid double input has been provided while asking for " + type.get() + ": "
                        + e.getMessage());
                System.out.print("We're sorrry! It seems that ");
                if (e.getClass().getCanonicalName().contains("Null")) {
                    System.out.print("you haven't provided any input. Try again - you're able.\n");
                } else {
                    System.out.print("you tried to paste something else then a number. Please use numbers only.\n");
                }
            }
        }
        log.info("A successful response has been provided for " + type + ": "
                + response);
        return response;
    }

    public static Integer additionalIntegerTests(Predicate<Integer> cond,
            Supplier<String> validationMsg, Supplier<String> type, Scanner sc) {
        int intermediary = validateDoubleValue(type, sc).intValue();
        while (!cond.test(intermediary)) {
            System.out.println(validationMsg.get());
            intermediary = validateDoubleValue(type, sc).intValue();
        }
        return intermediary;
    }
}
