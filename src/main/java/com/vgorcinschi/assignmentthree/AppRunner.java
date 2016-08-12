/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vgorcinschi.assignmentthree;

import com.vgorcinschi.assignmentthree.domain.YearMonthOps;
import static com.vgorcinschi.assignmentthree.util.NumberFormatter.additionalIntegerTests;
import static com.vgorcinschi.assignmentthree.util.NumberFormatter.validateDoubleValue;
import static com.vgorcinschi.assignmentthree.util.StringValidator.stringValidator;
import java.util.Scanner;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author vgorcinschi
 */
public class AppRunner {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {

            System.out.println("Let us print a calendar for a month of your choice");
            //year
            int year = additionalIntegerTests((i) -> {
                return i > 0 && i < 3000;
            }, () -> "The year must between 0 and 3000.", () -> "year", sc);

            //month
            int month = additionalIntegerTests((i) -> {
                return i > 0 && i < 13;
            }, () -> "Values between 1 and 12 allowed only.", () -> "month", sc);
            YearMonthOps ym = new YearMonthOps(year, month);
            ym.displayCurrentMonthAsACalendar();
            System.out.println("Would you like to try another month and check if we can determine "
                    + "which of the two comes first? (Y/N)");
            String decision = stringValidator((s) -> StringUtils.containsAny(s, "yYnN"),
                    () -> "Answer must be 'Y' or 'N' only", () -> "decision", sc);
            if (decision.equalsIgnoreCase("N")) {
                System.out.println("\nNo problem at all! Take care!");
                System.exit(0);
            }
            int anotherYear = additionalIntegerTests((i) -> {
                return i > 0 && i < 3000;
            }, () -> "The year must between 0 and 3000.", () -> "year", sc);

            //month
            int anotherMonth = additionalIntegerTests((i) -> {
                return i > 0 && i < 13;
            }, () -> "Values between 1 and 12 allowed only.", () -> "month", sc);
            YearMonthOps anotherYm = new YearMonthOps(anotherYear, anotherMonth);
            anotherYm.displayCurrentMonthAsACalendar();
            System.out.print("\nWell, ");
            if (ym.compareTo(anotherYm) < 0) {
                System.out.print(ym.getMonth() + " is before "
                        + anotherYm.getMonth());
            } else if (ym.compareTo(anotherYm) > 0) {
                System.out.print(anotherYm.getMonth() + " is before "
                        + ym.getMonth());
            } else {
                System.out.print(anotherYm.getMonth() + " and "
                        + ym.getMonth() + " are equal.");
            }
        } catch (Exception e) {
        }
    }
}
