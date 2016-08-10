/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vgorcinschi.assignmentthree.domain;

import static java.time.YearMonth.now;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author vgorcinschi
 */
public class YearMotnhOpsTests {
    private YearMonthOps month;
    
    public YearMotnhOpsTests() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void cannotPerformIllegalOperation(){
        month = new YearMonthOps(2016, 01);
        assertFalse(month.changeMonthBy('?', 7));
    }
    
    @Test
    public void yearMonthWasIncreasedByExample(){
        month = new YearMonthOps(now());
        month.changeMonthBy('+', 9);
        assertTrue(month.getMonthAsInt()==5);
    }
}
