package archive.filesystem;

import archive.model.ArchiveEntry;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class FileCollector {
    public List<ArchiveEntry> collect(List<String> paths) throws IOException {
        List<ArchiveEntry> entries = new ArrayList<>();
        for (String pathStr : paths) {
            Path path = Paths.get(pathStr);
            if (Files.isDirectory(path)) {
                Files.walk(path).filter(Files::isRegularFile).forEach(p -> {
                    try {
                        entries.add(new ArchiveEntry(p.toString()));
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                });
            } else {
                entries.add(new ArchiveEntry(pathStr));
            }
        }
        return entries;
    }
}
