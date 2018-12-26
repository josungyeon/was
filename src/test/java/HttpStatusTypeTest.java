import static org.junit.Assert.*;

import model.HttpStatusType;
import org.junit.Test;
import webserver.HttpRequest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by sungyeonjo on 2018. 12. 11..
 */
public class HttpStatusTypeTest {

    @Test
    public void testHttpStatus() throws FileNotFoundException {
        InputStream is = new FileInputStream(new File("./src/test/resources/HttpReq.txt"));
        HttpRequest httpRequest = new HttpRequest(is);

        HttpStatusType httpStatusType = HttpStatusType.getHttpStatusType(httpRequest);

        assertEquals(HttpStatusType.STATUS_CODE_404.getStatusCode(), httpStatusType.getStatusCode());
    }
}
