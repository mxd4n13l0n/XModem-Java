package com.skyfoxdigital.xmodem;

import org.junit.Assert;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class TestFileTransfer {

    @Test
    public void testBuildFileTransferWithPath() throws Exception {
        byte[] data = Files.readAllBytes(Paths.get("src/test/resources/rubick.jpg"));
        int size = data.length;
        int numberOfExpectedPackages = (size / 128) + 1; //(+1 of EOT packet)
        if (size % 128 > 1) {
            numberOfExpectedPackages++;
        }
        FileTransfer fileTransfer = new FileTransfer();
        List<byte[]> transfer = fileTransfer.build("src/test/resources/rubick.jpg");
        Assert.assertTrue(transfer.size() == numberOfExpectedPackages);
    }

    @Test(expected = Exception.class)
    public void testBuildFileTransferFileNotFound() throws Exception {
        FileTransfer fileTransfer = new FileTransfer();
        List<byte[]> transfer = fileTransfer.build("none.jpg");
    }
}
