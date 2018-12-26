package util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import model.ErrorPage;
import model.VirtualHost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;

/**
 * config.json 파일에 있는 값들을 파싱해서 가져옵니다.
 *
 * Created by sungyeonjo on 2018. 12. 9..
 */
public class ConfigurationUtils {
    private static final Logger logger = LoggerFactory.getLogger(ConfigurationUtils.class);

    public static final String RESOURCE_PATH = "createWas/src/main/resources";
    public static final String WEBAPP_PATH = "createWas/webapp";
    public static final String CONFIG_FILE_NAME = "/config.json";

    public static List<VirtualHost> CONF_LIST = new ArrayList<>();

    public static Map<String, String> ERROR_PAGE_MAP = new HashMap<>();

    public static String PORT;

    static  {
        VirtualHost[] virtualHosts;
        ErrorPage[] errorPages;

        try {
            File jsonFile = new File(RESOURCE_PATH + CONFIG_FILE_NAME);
            JsonObject jsonObject = new Gson().fromJson(new FileReader(jsonFile), JsonObject.class);

            virtualHosts = new Gson().fromJson(jsonObject.get("virtualHost"), VirtualHost[].class);
            CONF_LIST = Arrays.asList(virtualHosts);

            PORT = jsonObject.get("port").getAsString();

            errorPages = new Gson().fromJson(jsonObject.get("errorPage"), ErrorPage[].class);
            for (ErrorPage errorPage: Arrays.asList(errorPages)) {
                ERROR_PAGE_MAP.put(errorPage.getErrorCode(), errorPage.getPage());
            }

        } catch (IOException e) {
            logger.error("Json File Read ERROR", e);
        }
    }
}
