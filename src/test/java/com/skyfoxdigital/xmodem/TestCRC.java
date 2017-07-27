package com.skyfoxdigital.xmodem;

import org.junit.Assert;
import org.junit.Test;

public class TestCRC extends TestCases {


    @Test
    public void testCRC() {
        for (String test: tests.keySet()) {
            Assert.assertEquals(tests.get(test).intValue(), new CRC16Xmodem().getCRC(test));
        }
    }
}
