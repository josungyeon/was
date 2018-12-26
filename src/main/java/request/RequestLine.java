package request;

import util.HttpUtils;

/**
 * Created by sungyeonjo on 2018. 12. 8..
 */
public class RequestLine {
    private String httpMethod;
    private String path;
    private String queryString;

    public RequestLine(String line) {
        String[] splitedRequestLine = line.split(" ");
        this.httpMethod = splitedRequestLine[0];

        String[] path = splitedRequestLine[1].split("\\?");
        this.path = path[0];

        if (path.length == 2) {
            this.queryString = path[1];
        }
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public String getPath() {
        if (path.equals("/")) {
            return HttpUtils.DEFAULT_HTML;
        }
        return path;
    }

    public String getQueryString() {
        return queryString;
    }

}
