package error;

import model.HttpStatusType;
import org.apache.commons.lang.StringUtils;
import webserver.HttpRequest;

/**
 * Created by sungyeonjo on 2018. 12. 10..
 */
public class ErrorHandler {
    private String path;
    private String statusCode;

    public ErrorHandler(HttpRequest request) {
        this.path = request.getPath();

        HttpStatusType httpStatusType = HttpStatusType.getHttpStatusType(request);

        String errorPath = httpStatusType.findErrorPathByStatusCode(httpStatusType.getStatusCode());
        if (StringUtils.isNotBlank(errorPath)) {
            this.path = errorPath;
        }
    }

    public String getHttpPath() {
        return this.path;
    }

    public String getStatusCode() {
        return this.statusCode;
    }
}
