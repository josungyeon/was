package filter;

import webserver.HttpRequest;

/**
 * Created by sungyeon on 26/12/2018.
 */
public class AuthenticationFilter implements Filter {

    @Override
    public void execute(HttpRequest httpRequest) {
        String path = httpRequest.getPath();

//        path.endsWith(".exe") || path.contains("/..");
    }
}
