package com.skyfoxdigital.xmodem;

import org.junit.Before;

import java.util.HashMap;
import java.util.Map;

public abstract class TestCases {
    protected CRC16Xmodem crc;
    protected Map<String, Integer> tests;

    @Before
    public void build() {
        crc = new CRC16Xmodem();
        //https://www.lammertbies.nl/comm/info/crc-calculation.html
        tests = new HashMap<String, Integer>();
        tests.put("hello",      0xC362);
        tests.put("123456789",  0x31C3);
        tests.put("ABCDE",      0xA559);
        tests.put("abcde",      0x3EE1);
        tests.put("Skyfox Digital",      0xFAF4);
        tests.put(" ",          0x2462);
        tests.put("!@",      0x7D13);
        tests.put("I love java",      0x6CE3);
        tests.put("mepos",      0x50D9);
        tests.put("1,2,3,4,5",      0xD423);
    }
}
