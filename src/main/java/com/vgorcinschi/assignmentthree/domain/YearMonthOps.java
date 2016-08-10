/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vgorcinschi.assignmentthree.domain;

import static java.lang.String.valueOf;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author vgorcinschi
 */
public class YearMonthOps {

    private final Logger log = LogManager.getLogger("YearMonthOps");

    private YearMonth month;
    private String[] array;
    private final String[] daysOfWeek = {"M ", "Tu", "W ", "Th", "F ", "Sa", "Su"};
    private final int id;
    private static final AtomicInteger ids = new AtomicInteger(1);

    private YearMonthOps() {
        id = ids.getAndIncrement();
    }

    public YearMonthOps(int year, int month) {
        this();
        //initial set-up
        this.month = YearMonth.of(year, month);
        populateArray();
    }

    public YearMonthOps(YearMonth month) {
        this();
        this.month = month;
        populateArray();
    }

    public YearMonth getMonth() {
        return month;
    }

    public String[] getArray() {
        return array;
    }

    public void changeYearTo(int year) {
        month = month.withYear(year);
        populateArray();
    }

    public void changeMonthInThisYearTo(int month) {
        this.month = this.month.withMonth(month);
        populateArray();
    }

    public boolean changeMonthBy(char operation, int months) {
        String logPreffix = this.getClass().getSimpleName() + " with id " + getId() + " ";
        switch (operation) {
            case '-':
                month = month.minusMonths((long) months % 12);
                populateArray();
                log.info(logPreffix + " was decremented by " + (months % 12) + " months");
                return true;
            case '+':
                month = month.plusMonths((long) months % 12);
                populateArray();
                log.info(logPreffix + " was incremented by " + (months % 12) + " months");
                return true;
            default:
                log.error(logPreffix + " could not be modified: '"
                        + " " + operation + "' is not a valid operation");
                return false;
        }
    }

    public int getId() {
        return id;
    }

    public int getMonthAsInt() {
        //January is 1, December is 12
        return month.getMonthValue();
    }

    public int getYearAsInt() {
        return month.getYear();
    }

    public void populateArray() {
        //starting to prepare the array
        List<String> futureArray = new LinkedList<>(Arrays.asList(daysOfWeek));
        //javadoc: the day-of-month to use, from 1 to 31 then later's day of week: 1 to 7
        int weekDayOfTheFirstDay = month.atDay(1).getDayOfWeek().getValue();
        //+padding before
        int paddingBefore;
        paddingBefore = weekDayOfTheFirstDay - (weekDayOfTheFirstDay - 1);
        for (int i = 0; i < paddingBefore; i++) {
            futureArray.add("  ");
        }
        /*
         * adding the weekdays of current month month.lengthOfMonth() -> number
         * of days in current month
         */
        for (int k = 1; k <= month.lengthOfMonth(); k++) {
            //pad the days less then 9 to occupy equal amount of positions
            if (month.atDay(k).getDayOfMonth() < 10) {
                futureArray.add(valueOf(month.atDay(k).getDayOfMonth()).concat(" "));
            } else {
                futureArray.add(valueOf(month.atDay(k).getDayOfMonth()));
            }
        }
        //+padding after
        while (futureArray.size() % 7 != 0) {
            futureArray.add("  ");
        }
        array = futureArray.toArray(new String[futureArray.size()]);
    }

    public void displayCurrentMonthAsACalendar() {
        System.out.println("\n"+month.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH)
                + "\t" + month.getYear());
        System.out.println("--------------------");
        for (int i = 1; i <=array.length; i++) {
           System.out.print(array[i-1]+" ");
            if (i % 7 == 0) {
                System.out.println("");
            }
            
        }
    }
}
