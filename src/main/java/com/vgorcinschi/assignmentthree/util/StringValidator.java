/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vgorcinschi.assignmentthree.util;

import static java.util.Optional.ofNullable;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.function.Supplier;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author vgorcinschi
 */
public class StringValidator {

    private static final Logger log = LogManager.getLogger("StringValidator");

    public static String stringValidator(Predicate<String> cond,
            Supplier<String> validationMsg, Supplier<String> type, Scanner sc) {
        String response = null;
        boolean responseReady = false;
        System.out.println("Please enter your " + type.get());
        while (!responseReady) {
            String unsanitized = sc.nextLine();
            if (!isNotBlank(unsanitized)) {
                log.error("An invalid input has been provided while asking for " + type.get());
                System.out.println("You cannot enter blank name. Please try again.");
            } else if (ofNullable(cond).isPresent()) {
                if (!cond.test(unsanitized)) {
                    log.error("An invalid input has been provided(" + unsanitized + ") "
                            + "while asking for " + type.get()
                            + ". Additional requirements failed: " + validationMsg.get());
                    System.out.println(isNotBlank(validationMsg.get()) ? validationMsg.get() : "You haven't "
                            + "meat caller's additional requirements. Unfortunately he hasn't provided "
                            + "any validation details.");
                } else {
                    responseReady = true;
                    response = unsanitized;
                    log.info("A successful response has been provided for " + type.get() + ": " + response);
                }
            } else {
                responseReady = true;
                response = unsanitized;
                log.info("A successful response has been provided for " + type.get() + ": " + response);
            }
        }
        return response;
    }
}
