package archive.compression;

public interface Compression {
    byte[] compress(byte[] data);
    byte[] decompress(byte[] data);
}
