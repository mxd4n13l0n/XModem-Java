package com.skyfoxdigital.xmodem;

import org.junit.Assert;
import org.junit.Test;

import java.nio.ByteBuffer;

public class TestXModem extends TestCases {

    @Test
    public void testXmodem() {
        byte seq = 0;
        for (String test: tests.keySet()) {
            byte[] word = test.getBytes();
            XmodemPacket xmodem = new XmodemPacket(word, ++seq);
            byte[] expected = new byte[133];
            expected[0] = 0x01;

            expected[1] = seq;
            expected[2] = (byte)~seq;

            System.arraycopy(word, 0, expected, 3, word.length);

            byte[] crcArray = ByteBuffer.allocate(4).putInt(new CRC16Xmodem().getCRC(test)).array();
            expected[131] = crcArray[2];
            expected[132] = crcArray[3];
            Assert.assertArrayEquals(expected, xmodem.getBytes());
        }

    }
}
