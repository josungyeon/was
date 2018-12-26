import org.junit.Test;
import webserver.HttpResponse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Created by sungyeonjo on 2018. 12. 11..
 */
public class HttpResponseTest {
    @Test
    public void testHttpResponse() throws FileNotFoundException {
        OutputStream out = new FileOutputStream(new File("test.html"));
        HttpResponse httpResponse = new HttpResponse(out);
        httpResponse.settingBody("/index.html", "200");
    }
}
