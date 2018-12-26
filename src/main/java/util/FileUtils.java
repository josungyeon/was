package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Created by sungyeonjo on 2018. 12. 10..
 */
public class FileUtils {
    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

    static public byte[] getFileBody(String path) {
        byte[] body = new byte[0];
        try {
            body = Files.readAllBytes(new File(path).toPath());
        } catch (IOException e) {
            logger.warn("No file. file path: " + path);
        }
        return body;
    }

    static public boolean isEmptyByteArray(byte[] bytes) {
        return bytes.length == 0;
    }

    static public boolean noExistFile(String path) {
        byte[] body = getFileBody(path);
        return isEmptyByteArray(body);
    }
}
