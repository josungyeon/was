package webserver;

/**
 * Created by sungyeonjo on 2018. 12. 8..
 */
public class RequestLine {
    private String httpMethod;
    private String fileName;

    public RequestLine(String line) {
        String[] tokens = line.split(" ");
        httpMethod = tokens[0];
        fileName = tokens[1];
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public String getFileName() {
        return fileName;
    }
}
