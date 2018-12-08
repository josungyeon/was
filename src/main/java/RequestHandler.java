import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webserver.RequestLine;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;

/**
 * Created by sungyeonjo on 2018. 12. 8..
 */
public class RequestHandler extends Thread {
    private static final Logger logger = LoggerFactory.getLogger(RequestHandler.class);

    private RequestLine requestLine;
    private Socket connection;

    public RequestHandler(Socket connectionSocket) {
        this.connection = connectionSocket;
    }

    public void run() {
        try (InputStream in = connection.getInputStream();
             OutputStream out = connection.getOutputStream()) {

            BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String line = br.readLine();
            logger.debug("readLine: {}", line);

            if (line == null) return;
            requestLine = new RequestLine(line);

            String page = getDefaultPage(requestLine);

            byte[] body = Files.readAllBytes(new File("createWas/webapp" + page).toPath());

            DataOutputStream dos = new DataOutputStream(out);
            response200Header(dos, body.length);
            responseBody(dos, body);

        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    private void responseBody(DataOutputStream dos, byte[] body) {
        try {
            dos.write(body, 0, body.length);
            dos.writeBytes("\r\n");
            dos.flush();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    private void response200Header(DataOutputStream dos, int lengthOfBodyContent) {
        try {
            dos.writeBytes("HTTP/1.1 200 OK \r\n");
            dos.writeBytes("Content-Type: text/html;charset=utf-8\r\n");
            dos.writeBytes("Content-Length: " + lengthOfBodyContent + "\r\n");
            dos.writeBytes("\r\n");
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    private String getDefaultPage(RequestLine requestLine) {
        File file = new File("createWas/src/main/resources/config.json");
        JSONParser parser = new JSONParser();
        String fileName = requestLine.getFileName();

        try {
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(file));

            // 403 예외 처리. 404, 500 추가될경우 예외 처리 고려해야함
            if (requestLine.getFileName().endsWith(".exe")) {
                String jsonFileKey = String.format("response%sfile", HttpStatusCodeType.STATUS_CODE_403.getStatusCode());
                fileName = (String) jsonObject.get(jsonFileKey);
            } else {
                fileName = "/index.html";
            }

        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } catch (ParseException e) {
            logger.error(e.getMessage(), e);
        }

        return fileName;
    }

}
