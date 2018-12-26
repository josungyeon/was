package servlet;

import webserver.HttpRequest;
import webserver.HttpResponse;

/**
 * Created by sungyeonjo on 2018. 12. 8..
 */
public interface SimpleServlet {
    void service(HttpRequest req, HttpResponse res);
}
