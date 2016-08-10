/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vgorcinschi.assignmentthree;

import com.vgorcinschi.assignmentthree.domain.YearMonthOps;

/**
 *
 * @author vgorcinschi
 */
public class AppRunner {
    public static void main(String[] args) {
        YearMonthOps ym = new YearMonthOps(2016, 10);
        ym.displayCurrentMonthAsACalendar();
    }
}
