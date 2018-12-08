/**
 * Created by sungyeonjo on 2018. 12. 8..
 */
public enum HttpStatusCodeType {
    STATUS_CODE_403("403"),
    STATUS_CODE_404("404"),
    STATUS_CODE_500("500"),
    STATUS_CODE_200("200");

    private String statusCode;

    HttpStatusCodeType(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public boolean is(String statusCode) {
        return this.statusCode.equals(statusCode);
    }
}
