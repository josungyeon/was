package webserver;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import request.RequestLine;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sungyeonjo on 2018. 12. 8..
 */
public class HttpRequest {
    private static final Logger logger = LoggerFactory.getLogger(HttpRequest.class);

    private RequestLine requestLine;
    private Map<String, String> headerMap = new HashMap<>();
    private Map<String, String> parameterMap = new HashMap<>();

    public HttpRequest(InputStream in) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String line = br.readLine();
            logger.debug("readLine: {}", line);
            if (line == null) return;

            requestLine = new RequestLine(line);
            parameterMap = addQueryString(requestLine);
            headerMap = settingHeaderMap(br);

            logger.info("host: {}", headerMap.get("Host"));

        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage(), e);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public String getHeader(String headerKey) {
        return headerMap.get(headerKey);
    }

    public String getParameter(String name) {
        return parameterMap.get(name);
    }

    public String getPath() {
        return requestLine.getPath();
    }

    private Map<String, String> settingHeaderMap(BufferedReader br) throws IOException {
        Map<String, String> map = new HashMap<>();
        String line;

        while (!(line = br.readLine()).equals("")) {
            String[] splitedHeaders = line.split(":");
            map.put(splitedHeaders[0], splitedHeaders[1].trim());
        }
        logger.debug("headerMap: {}", map);

        return map;
    }

    private Map<String, String> addQueryString(RequestLine requestLine) {
        String queryString = requestLine.getQueryString();
        if (StringUtils.isEmpty(queryString)) {
            return parameterMap;
        }
        String[] key = queryString.split("=");
        parameterMap.put(key[0], key[1]);

        return parameterMap;
    }
}
