package archive.serialization;

import archive.compression.Compression;
import archive.model.Archive;
import archive.model.ArchiveEntry;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class ArchiveWriter {
    private static final byte[] MAGIC = "MYAR".getBytes(StandardCharsets.UTF_8);
    private static final int VERSION = 1;

    public void write(Archive archive, Compression compression, String outputPath) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(outputPath)))) {
            dos.write(MAGIC);
            dos.writeInt(VERSION);

            dos.writeInt(archive.getEntries().size());

            for (ArchiveEntry entry : archive.getEntries()) {
                byte[] pathBytes = entry.getFilePath().toString().getBytes(StandardCharsets.UTF_8);
                byte[] rawData = Files.readAllBytes(entry.getFilePath());
                byte[] compressedData = compression.compress(rawData);

                dos.writeInt(pathBytes.length);
                dos.write(pathBytes);

                dos.writeLong(entry.getSize());

                dos.writeLong(entry.getChecksum());

                dos.writeInt(compressedData.length);
                dos.write(compressedData);
            }
        }
    }
}