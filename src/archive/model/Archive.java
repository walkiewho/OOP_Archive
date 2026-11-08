package archive.model;

import java.util.ArrayList;

public class Archive {
    private final ArrayList<ArchiveEntry> entries = new ArrayList<>();

    public void addEntry(ArchiveEntry entry) {
        this.entries.add(entry);
    }

    public ArrayList<ArchiveEntry> getEntries() {
        return entries;
    }
}
