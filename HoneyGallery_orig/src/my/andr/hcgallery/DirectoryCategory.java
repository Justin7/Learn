package my.andr.hcgallery;

public class DirectoryCategory {
    private String name;
    private DirectoryEntry[] entries;

    public DirectoryCategory(String name, DirectoryEntry[] entries) {
        this.name = name;
        this.entries = entries;
    }
    public String getName() {
        return name;
    }
    public int getEntryCount() {
        return entries.length;
    }
    public DirectoryEntry getEntry(int i) {
        return entries[i];
    }
}
