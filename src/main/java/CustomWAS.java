import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by sungyeonjo on 2018. 12. 7..
 */
public class CustomWAS {
    private static final Logger logger = LoggerFactory.getLogger(CustomWAS.class);

    public static void main(String[] args) {
        int port = 8000;

        try (ServerSocket listenSocket = new ServerSocket(port)) {
            logger.info("WAS started {} port", port);

            Socket connection;
            while ((connection = listenSocket.accept()) != null) {
                RequestHandler requestHandler = new RequestHandler(connection);
                requestHandler.start();
            }

        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
