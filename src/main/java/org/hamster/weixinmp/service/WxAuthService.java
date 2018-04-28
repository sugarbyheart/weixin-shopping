/**
 *
 */
package org.hamster.weixinmp.service;

import lombok.extern.slf4j.Slf4j;
import org.hamster.weixinmp.config.WxConfig;
import org.hamster.weixinmp.dao.entity.auth.WxAuth;
import org.hamster.weixinmp.dao.entity.auth.WxAuthReq;
import org.hamster.weixinmp.exception.WxException;
import org.hamster.weixinmp.util.WxUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * @author grossopaforever@gmail.com
 * @version Jan 1, 2014
 */
@Slf4j
@Service
public class WxAuthService {

    public static final String TEST_FOLDER = "src/resources/tmp";
    public static final String ACCESS_TOKEN_FILE = TEST_FOLDER + "/accessToken.txt";

    @Autowired
    protected WxConfig config;

    private String accessToken;

    public String getAccessToken() throws WxException, IOException {
        File testConfigFile = new File(ACCESS_TOKEN_FILE);
        Date currentDate = new Date();
        if (!testConfigFile.exists() || currentDate.getTime() - testConfigFile.lastModified() > 3600000) {
            WxAuth auth = this.getAccessToken(config.getAppid(), config.getAppsecret());
            this.accessToken = auth.getAccessToken();
            new File(TEST_FOLDER).mkdirs();
            FileWriter writer = new FileWriter(ACCESS_TOKEN_FILE);
            writer.write(accessToken);
            writer.flush();
            writer.close();
        } else {
            BufferedReader reader = new BufferedReader(new FileReader(ACCESS_TOKEN_FILE));
            this.accessToken = reader.readLine();
            reader.close();
        }
        return this.accessToken;
    }

    public WxAuth getAccessToken(String appid, String appsecret)
            throws WxException {
        Map<String, String> paramsJson = new HashMap<String, String>();
        paramsJson.put("grant_type", "client_credential");
        paramsJson.put("appid", appid);
        paramsJson.put("secret", appsecret);

        WxAuth result = WxUtil.sendRequest(config.getAccessTokenCreateUrl(),
                HttpMethod.GET, paramsJson, null, WxAuth.class);
        result.setGrantType("client_credential");
        result.setAppid(appid);
        result.setSecret(appsecret);
        return result;
    }

    public boolean validateAuth(String signature, String timestamp,
                                String nonce, String echostr) throws WxException {
        WxAuthReq authReq = new WxAuthReq();
        authReq.setCreatedDate(new Date());
        authReq.setSignature(signature);
        authReq.setTimestamp(timestamp);
        authReq.setNonce(nonce);
        authReq.setEchostr(echostr);

        String excepted = hash(getStringToHash(timestamp, nonce,
                config.getToken()));

        if (signature == null || !signature.equals(excepted)) {
            log.error("Authentication failed! excepted echostr ->" + excepted);
            log.error("                                 actual ->" + signature);
            return false;
        }

        return true;
    }

    protected static String getStringToHash(String timestamp, String nonce,
                                            String token) {
        List<String> list = new ArrayList<String>();
        list.add(timestamp);
        list.add(nonce);
        list.add(token);

        String result = "";
        Collections.sort(list);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
            result += list.get(i);
        }
        return result;
    }

    protected static String hash(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] b = md.digest(str.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < b.length; i++) {
                sb.append(Integer.toString((b[i] & 0xff) + 0x100, 16)
                        .substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            // never happens
        }
        return null;
    }

}
