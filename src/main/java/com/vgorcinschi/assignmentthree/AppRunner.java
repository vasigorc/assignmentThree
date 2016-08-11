/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vgorcinschi.assignmentthree;

import com.vgorcinschi.assignmentthree.domain.YearMonthOps;
import static com.vgorcinschi.assignmentthree.util.NumberFormatter.additionalIntegerTests;
import static com.vgorcinschi.assignmentthree.util.NumberFormatter.validateDoubleValue;
import java.util.Scanner;

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
            },() -> "The year must between 0 and 3000.", () -> "year", sc);
            
            //month
            int month = additionalIntegerTests((i) -> {
                return i > 0 && i < 13;
            },() -> "Values between 1 and 12 allowed only.", () -> "month", sc);
            YearMonthOps ym = new YearMonthOps(year, month);
            ym.displayCurrentMonthAsACalendar();
        } catch (Exception e) {
        }
    }
}
