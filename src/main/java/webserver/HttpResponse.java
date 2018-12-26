package webserver;

import model.HttpStatusType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.ConfigurationUtils;
import util.FileUtils;
import util.HttpUtils;

import java.io.*;

/**
 * Created by sungyeonjo on 2018. 12. 8..
 */
public class HttpResponse {
    private static final Logger logger = LoggerFactory.getLogger(HttpResponse.class);

    private DataOutputStream dos;

    public HttpResponse(OutputStream out) {
        dos = new DataOutputStream(out);
    }

    public void settingBody(String path, String statusCode) {
        byte[] body = FileUtils.getFileBody(ConfigurationUtils.WEBAPP_PATH + path);

        responseHeader(body.length, statusCode);
        responseBody(body);
    }

    private void responseBody(byte[] body) {
        try {
            dos.write(body, 0, body.length);
            dos.writeBytes("\r\n");
            dos.flush();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    private void responseHeader(int lengthOfBodyContent, String statusCode) {
        try {
            dos.writeBytes(String.format(HttpUtils.RESPONSE_HEADER_FORMAT, HttpStatusType.findByStatusCode(statusCode)));
            dos.writeBytes("Content-Type: text/html;charset=utf-8\r\n");
            dos.writeBytes("Content-Length: " + lengthOfBodyContent + "\r\n");
            dos.writeBytes("\r\n");
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
