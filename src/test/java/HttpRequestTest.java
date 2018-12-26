import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;
import webserver.HttpRequest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by sungyeonjo on 2018. 12. 10..
 */
public class HttpRequestTest {
    @Test
    public void testGetHttpRequest() throws FileNotFoundException {
        InputStream is = new FileInputStream(new File("./src/test/resources/HttpReq.txt"));
        HttpRequest httpRequest = new HttpRequest(is);

        assertEquals("/index.html", httpRequest.getPath());
        assertEquals("*/*", httpRequest.getHeader("Accept"));
        assertEquals("keep-alive", httpRequest.getHeader("Connection"));
        assertEquals("localhost", httpRequest.getHeader("Host"));
        assertEquals("sy", httpRequest.getParameter("name"));
    }
}
