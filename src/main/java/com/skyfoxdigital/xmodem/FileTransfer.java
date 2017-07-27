package com.skyfoxdigital.xmodem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileTransfer {
    private static final byte[] END_OF_TRANSFER = new byte[]{0x04};

    public List<byte[]> build(String path) throws Exception {
        return this.build(new FileInputStream(new File(path)));
    }

    public List<byte[]> build(byte[] byteArray) throws Exception {
        return this.build(new ByteArrayInputStream(byteArray));
    }

    public List<byte[]> build(InputStream is) throws Exception {
        int sequence = 0;
        List<byte[]> packetList = new ArrayList<>();
        byte[] buffer = new byte[128];
        int offset = 0;
        BufferedInputStream buf = new BufferedInputStream(is);
        while (buf.read(buffer, offset, buffer.length) > 0) {
            packetList.add(new XmodemPacket(buffer, ++sequence).getBytes());
        }
        packetList.add(END_OF_TRANSFER);
        buf.close();
        return packetList;
    }
}
