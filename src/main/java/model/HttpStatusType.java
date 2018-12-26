package model;

import util.ConfigurationUtils;
import util.FileUtils;
import webserver.HttpRequest;

/**
 * Created by sungyeonjo on 2018. 12. 10..
 */
public enum HttpStatusType {
    STATUS_CODE_200("200", "200 OK") {
        @Override
        boolean check(HttpRequest httpRequest) {
            return false;
        }
    },

    STATUS_CODE_403("403", "403 Forbidden") {
        @Override
        boolean check(HttpRequest httpRequest) {
            String path = httpRequest.getPath();
            return path.endsWith(".exe") || path.contains("/..");
        }
    },

    STATUS_CODE_404("404", "404 Not Found") {
        @Override
        boolean check(HttpRequest httpRequest) {
            // file not exists = 404 error
            String path = ConfigurationUtils.WEBAPP_PATH + httpRequest.getPath();
            return FileUtils.noExistFile(path);
        }
    };

    String statusCode;
    String headerMsg;

    abstract boolean check(HttpRequest httpRequest);

    HttpStatusType(String statusCode, String headerMsg) {
        this.statusCode = statusCode;
        this.headerMsg = headerMsg;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public String getHeaderMsg() {
        return headerMsg;
    }

    public static String findByStatusCode(String statusCode) {
        for (HttpStatusType type: HttpStatusType.values()) {
            if (type.getStatusCode().equals(statusCode)) {
                return type.getHeaderMsg();
            }
        }
        return STATUS_CODE_200.getHeaderMsg();
    }

    public static HttpStatusType getHttpStatusType(HttpRequest httpRequest) {
        for (HttpStatusType statusType: HttpStatusType.values()) {
            if (statusType.check(httpRequest)) {
                return statusType;
            }
        }
        return STATUS_CODE_200;
    }

    public static String findErrorPathByStatusCode(String statusCode) {
        return ConfigurationUtils.ERROR_PAGE_MAP.get(statusCode);
    }
}
