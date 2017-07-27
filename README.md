# XModem-Java
Tool for wrapping data into packets in XModem protocol ready to send to your end-point
This tool converts your data to a 128 byte packets, adding their CRC16 and adding the EOT (end of transfer) line.

# Usage
```Java
  FileTransfer fileTransfer = new FileTransfer();
  try {
    List<byte[]> dataChunks = fileTransfer.build("my_file_path"); //wraps your file path into chunks
    List<byte[]> dataChunks = fileTransfer.build(myByteArray); //wrap your byte[] data into chunks
    List<byte[]> dataChunks = fileTransfer.build(myInputStream); //read your InputStream into chunks
    for (byte[] dataChunk : dataChunks) {
      //chunks ready to send to your endpoint!
    }
  } catch (Exception e) {
    //your error handling
  }
  
```
