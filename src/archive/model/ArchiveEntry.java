package archive.model;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ArchiveEntry {
    private final Path filePath;
    private final long size;
    private final long checksum;


    public ArchiveEntry(String filePath) throws IOException {
        this.filePath = getPath(filePath);
        this.size = getFileSize();
        this.checksum = calcChecksum();

    }

    private Path getPath(String filePathString) throws FileNotFoundException {
        Path filePath = Paths.get(filePathString);

        if (Files.exists(filePath)) {
            return filePath;
        } else {
            throw new FileNotFoundException("No file or path");
        }
    }


    private long getFileSize() throws IOException {
        return Files.size(this.filePath);
    }


    private long calcChecksum() throws IOException {
        int xor = 0;
        try (InputStream in = new BufferedInputStream(Files.newInputStream(this.filePath))) {
            int b;
            while ((b = in.read()) != -1) {
                xor ^= b;
            }
        }
        return xor;
    }

    public Path getFilePath() { return filePath; }
    public long getSize() { return size; }
    public long getChecksum() { return checksum; }
}
