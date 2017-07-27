package com.skyfoxdigital.xmodem;

import java.nio.ByteBuffer;

public class XmodemPacket {
    private static final int POS_START_OF_HEADER = 0;
    private static final int POS_PACKET_NUM = 1;
    private static final int POS_PACKET_NUM_INVERSE = 2;
    private static final int POS_START_OF_DATA = 3;
    private static final int POS_CRC_1 = 131;
    private static final int POS_CRC_2 = 132;

    public static final byte SOH = 0x01;
    private byte[] packet;

    public XmodemPacket(byte[] word, int packetNumber) {
        packet = new byte[133];
        int maxLength = word.length > 128? 128 : word.length;
        packet[POS_START_OF_HEADER] = SOH;
        packet[POS_PACKET_NUM] = (byte)packetNumber;
        packet[POS_PACKET_NUM_INVERSE] = (byte)~packetNumber;
        System.arraycopy(word, 0, packet, POS_START_OF_DATA, maxLength);
//        int crc = new CRC16Xmodem().getCRC(word);
//        ByteBuffer b = ByteBuffer.allocate(4);
//        b.putInt(crc);
//        byte[] crcByteArray = b.array();
        byte[] crcByteArray = new CRC16Xmodem().calCrc(packet, POS_START_OF_DATA, 128);
//        packet[POS_CRC_1] = crcByteArray[2];
//        packet[POS_CRC_2] = crcByteArray[3];
        packet[POS_CRC_1] = crcByteArray[0];
        packet[POS_CRC_2] = crcByteArray[1];
    }

    public byte[] getBytes() {
        return packet;
    }
}
