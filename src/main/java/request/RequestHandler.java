package request;

import error.ErrorHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webserver.HttpRequest;
import webserver.HttpResponse;

import java.io.*;
import java.net.Socket;

/**
 * Created by sungyeonjo on 2018. 12. 8..
 */
public class RequestHandler extends Thread {
    private static final Logger logger = LoggerFactory.getLogger(RequestHandler.class);

    private Socket connection;

    public RequestHandler(Socket connectionSocket) {
        this.connection = connectionSocket;
    }

    public void run() {
        try (InputStream in = connection.getInputStream(); OutputStream out = connection.getOutputStream()) {
            HttpRequest request = new HttpRequest(in);

            // error handling
            ErrorHandler handler = new ErrorHandler(request);
            String statusCode = handler.getStatusCode();
            String path = handler.getHttpPath();

            HttpResponse response = new HttpResponse(out);
            response.settingBody(path, statusCode);

        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
