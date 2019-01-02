package filter;

import webserver.HttpRequest;

/**
 * Created by sungyeon on 26/12/2018.
 */
public interface Filter {
    void execute(HttpRequest httpRequest);
}
