package lk.ijse.workoutplanbackend.util;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Base64;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Component
public class CompressionUtil {

    public String compress(String data) throws Exception {
        Deflater deflater = new Deflater();
        deflater.setInput(data.getBytes());
        deflater.finish();
        byte[] buffer = new byte[1024];
        int length = deflater.deflate(buffer);
        return Base64.getEncoder().encodeToString(Arrays.copyOf(buffer, length));

    }

    public String decompress(String compressedData) throws Exception {
        byte[] data = Base64.getDecoder().decode(compressedData);
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        byte[] buffer = new byte[1024];
        int length = inflater.inflate(buffer);
        return new String(buffer, 0, length);
    }
}
