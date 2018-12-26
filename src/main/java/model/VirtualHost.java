package model;

/**
 * Created by sungyeonjo on 2018. 12. 9..
 */
public class VirtualHost {
    String documentRoot;
    String serverName;

    public String getDocumentRoot() {
        return documentRoot;
    }

    public String getServerName() {
        return serverName;
    }

    @Override
    public String toString() {
        return getDocumentRoot() + ", " + getServerName();
    }
}
