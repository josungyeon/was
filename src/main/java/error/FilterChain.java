package error;

import com.google.common.collect.Lists;
import filter.Filter;
import webserver.HttpRequest;

import java.util.List;

/**
 * Created by sungyeon on 26/12/2018.
 */
public class FilterChain {
    private List<Filter> filters = Lists.newArrayList();

    public void execute(HttpRequest httpRequest) {
        for (Filter filter: filters) {
            filter.execute(httpRequest);
        }
    }
}
