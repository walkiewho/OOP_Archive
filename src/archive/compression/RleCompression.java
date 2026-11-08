package archive.compression;

import java.io.ByteArrayOutputStream;


public class RleCompression implements Compression {

    @Override
    public byte[] compress(byte[] data) {
        if (data.length == 0) return new byte[0];
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        int i = 0;
        while (i < data.length) {
            byte current = data[i];
            int count = 1;

            while (i + count < data.length && data[i + count] == current && count < 255) {
                count++;
            }
            out.write(count);
            out.write(current);
            i += count;
        }
        return out.toByteArray();
    }

    @Override
    public byte[] decompress(byte[] data) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int i = 0;
        while (i < data.length) {
            int count = data[i++] & 0xFF;
            byte val = data[i++];
            for (int j = 0; j < count; j++) {
                out.write(val);
            }
        }
        return out.toByteArray();
    }
}
